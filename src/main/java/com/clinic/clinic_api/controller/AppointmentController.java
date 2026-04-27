package com.clinic.clinic_api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.clinic_api.dto.AppointmentRequest;
import com.clinic.clinic_api.dto.AppointmentResponse;
import com.clinic.clinic_api.dto.UpdateNotesRequest;
import com.clinic.clinic_api.mapper.AppointmentMapper;
import com.clinic.clinic_api.model.Appointment;
import com.clinic.clinic_api.service.AppointmentService;

import org.springframework.web.bind.annotation.RequestBody; 
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping
    public AppointmentResponse create(@Valid @RequestBody AppointmentRequest req) {
        Appointment a = AppointmentMapper.toEntity(req);
        return AppointmentMapper.toResponse(service.create(a));
    }

    @GetMapping("/{id}")
    public AppointmentResponse get(@PathVariable String id) {
        return AppointmentMapper.toResponse(service.getById(id));
    }

    @PatchMapping("/{id}/cancel")
    public AppointmentResponse cancel(@PathVariable String id) {
        return AppointmentMapper.toResponse(service.cancel(id));
    }

    @PatchMapping("/{id}")
    public AppointmentResponse updateNotes(
            @PathVariable String id,
            @RequestBody UpdateNotesRequest req) {

        return AppointmentMapper.toResponse(
                service.updateNotes(id, req.getNotes())
        );
    }

	/*
	 * @GetMapping public List<AppointmentResponse> list(
	 * 
	 * @RequestParam String practitionerId) {
	 * 
	 * return service.getAll(practitionerId) .stream()
	 * .map(AppointmentMapper::toResponse) .collect(Collectors.toList()); }
	 */
    
    @GetMapping
    public List<AppointmentResponse> list(
            @RequestParam String practitionerId,
            @RequestParam String date,
            @RequestParam int page,
            @RequestParam int size) {

        return service.getAppointments(practitionerId, date, page, size)
                .stream()
                .map(AppointmentMapper::toResponse)
                .toList();
    }
}