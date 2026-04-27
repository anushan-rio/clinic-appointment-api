package com.clinic.clinic_api.controller;



import com.clinic.clinic_api.dto.*;
import com.clinic.clinic_api.mapper.PractitionerMapper;
import com.clinic.clinic_api.model.Practitioner;
import com.clinic.clinic_api.service.PractitionerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/practitioners")
public class PractitionerController {

    private final PractitionerService service;

    public PractitionerController(PractitionerService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public PractitionerResponse create(@Valid @RequestBody PractitionerRequest request) {
        Practitioner p = PractitionerMapper.toEntity(request);
        Practitioner saved = service.create(p);
        return PractitionerMapper.toResponse(saved);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public PractitionerResponse getById(@PathVariable String id) {
        return PractitionerMapper.toResponse(service.getById(id));
    }

    // GET ALL
    @GetMapping
    public List<PractitionerResponse> getAll() {
        return service.getAll()
                .stream()
                .map(PractitionerMapper::toResponse)
                .collect(Collectors.toList());
    }
}