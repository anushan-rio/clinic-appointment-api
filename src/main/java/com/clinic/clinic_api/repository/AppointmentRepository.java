package com.clinic.clinic_api.repository;

import org.springframework.data.domain.Pageable; 
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.clinic.clinic_api.model.Appointment;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {

    List<Appointment> findByPractitionerIdAndStatus(String practitionerId, String status);

    List<Appointment> findByPractitionerId(String practitionerId);
    
    Page<Appointment> findByPractitionerIdAndStartTimeBetween(
            String practitionerId,
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable
    );
}