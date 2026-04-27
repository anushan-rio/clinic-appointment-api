package com.clinic.clinic_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.clinic.clinic_api.exception.ResourceNotFoundException;
import com.clinic.clinic_api.model.Patient;
import com.clinic.clinic_api.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public Patient create(Patient patient) {

        repository.findByNationalId(patient.getNationalId())
                .ifPresent(p -> {
                    throw new RuntimeException("National ID already exists");
                });

        return repository.save(patient);
    }

    public Patient getById(String id) {
        Optional<Patient> patientOptional = repository.findById(id);

        if (patientOptional.isPresent()) {
            return patientOptional.get();
        } else {
            throw new ResourceNotFoundException("Patient not found");
        }
    }

    public List<Patient> search(String nationalId, String name) {

        if (nationalId != null) {
            return repository.findByNationalId(nationalId)
                    .map(List::of)
                    .orElse(List.of());
        }

        if (name != null) {
            return repository.findByFullNameContainingIgnoreCase(name);
        }

        return repository.findAll();
    }
}
