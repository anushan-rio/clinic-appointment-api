package com.clinic.clinic_api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.clinic.clinic_api.exception.ConflictException;
import com.clinic.clinic_api.exception.ResourceNotFoundException;
import com.clinic.clinic_api.model.Appointment;
import com.clinic.clinic_api.notification.NotificationFactory;
import com.clinic.clinic_api.repository.AppointmentRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository repo;
    private final NotificationFactory notificationFactory;

    public AppointmentService(AppointmentRepository repo,
                              NotificationFactory notificationFactory) {
        this.repo = repo;
        this.notificationFactory = notificationFactory;
    }

   
 public Appointment create(Appointment appointment) {

        // null check
        if (appointment.getStartTime() == null || appointment.getEndTime() == null) {
            throw new ConflictException("Start time and End time are required");
        }

        // strict validation (IMPORTANT FIX)
        if (!appointment.getEndTime().isAfter(appointment.getStartTime())) {
            throw new ConflictException("End time must be after start time");
        }

        // default values
        appointment.setStatus("BOOKED");
        appointment.setCreatedAt(LocalDateTime.now());

        // conflict rule (MAIN LOGIC)
        List<Appointment> existing =
                repo.findByPractitionerIdAndStatus(
                        appointment.getPractitionerId(), "BOOKED");

        for (Appointment a : existing) {

            boolean overlap =
                    appointment.getStartTime().isBefore(a.getEndTime()) &&
                    appointment.getEndTime().isAfter(a.getStartTime());

            if (overlap) {
                throw new ConflictException("Appointment overlap detected");
            }
        }

        Appointment saved = repo.save(appointment);

        // notification
        notificationFactory.getStrategy()
                .send("Appointment booked...");

        return saved;
    }

   
 public Appointment getById(String id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Appointment not found"));
    }

   
 public Appointment cancel(String id) {

        Appointment a = getById(id);

        if (!"BOOKED".equals(a.getStatus())) {
            throw new ConflictException("Only booked appointments can be cancelled");
        }

        a.setStatus("CANCELLED");
        a.setUpdatedAt(LocalDateTime.now());

        notificationFactory.getStrategy()
                .send("Appointment cancelled...");

        return repo.save(a);
    }

  
 public Appointment updateNotes(String id, String notes) {

        Appointment a = getById(id);

        a.setNotes(notes);
        a.setUpdatedAt(LocalDateTime.now());

        notificationFactory.getStrategy()
                .send("Appointment updated...");

        return repo.save(a);
    }

    
 public List<Appointment> getAll(String practitionerId) {
        return repo.findByPractitionerId(practitionerId);
    }

  
 public Page<Appointment> getAppointments(
	        String practitionerId,
	        String date,
	        int page,
	        int size) {

	    LocalDate localDate = LocalDate.parse(date);

	    ZoneId zone = ZoneId.of("Asia/Colombo");

	    ZonedDateTime startZoned = localDate.atStartOfDay(zone);
	    ZonedDateTime endZoned = localDate.atTime(23, 59, 59, 999999999).atZone(zone);

	    LocalDateTime start = startZoned.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
	    LocalDateTime end = endZoned.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();

	    Pageable pageable = PageRequest.of(page, size);

	    return repo.findByPractitionerIdAndStartTimeBetween(
	            practitionerId, start, end, pageable
	    );
	}
}