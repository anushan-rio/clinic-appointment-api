package com.clinic.clinic_api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.clinic_api.dto.PatientRequest;
import com.clinic.clinic_api.dto.PatientResponse;
import com.clinic.clinic_api.mapper.PatientMapper;
import com.clinic.clinic_api.model.Patient;
import com.clinic.clinic_api.service.PatientService;

import org.springframework.web.bind.annotation.RequestBody; 
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public PatientResponse create(@Valid @RequestBody PatientRequest request) {
        Patient patient = PatientMapper.toEntity(request);
        Patient saved = service.create(patient);
        return PatientMapper.toResponse(saved);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public PatientResponse getById(@PathVariable String id) {
        return PatientMapper.toResponse(service.getById(id));
    }

    // SEARCH
    @GetMapping
    public List<PatientResponse> search(
            @RequestParam(required = false) String nationalId,
            @RequestParam(required = false) String name) {

        return service.search(nationalId, name)
                .stream()
                .map(PatientMapper::toResponse)
                .collect(Collectors.toList());
    }
}