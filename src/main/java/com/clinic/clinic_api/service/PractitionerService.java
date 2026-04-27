package com.clinic.clinic_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinic.clinic_api.exception.ResourceNotFoundException;
import com.clinic.clinic_api.model.Practitioner;
import com.clinic.clinic_api.repository.PractitionerRepository;

@Service
public class PractitionerService {

    private final PractitionerRepository repository;

    public PractitionerService(PractitionerRepository repository) {
        this.repository = repository;
    }

    public Practitioner create(Practitioner practitioner) {

        repository.findByRegistrationNo(practitioner.getRegistrationNo())
                .ifPresent(p -> {
                    throw new RuntimeException("Registration number already exists");
                });

        return repository.save(practitioner);
    }

    public Practitioner getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Practitioner not found"));
    }

    public List<Practitioner> getAll() {
        return repository.findAll();
    }
}