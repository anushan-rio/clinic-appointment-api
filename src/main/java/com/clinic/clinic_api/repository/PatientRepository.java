package com.clinic.clinic_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.clinic.clinic_api.model.Patient;

public interface PatientRepository extends MongoRepository<Patient, String> {

    Optional<Patient> findByNationalId(String nationalId);

    List<Patient> findByFullNameContainingIgnoreCase(String name);
}
