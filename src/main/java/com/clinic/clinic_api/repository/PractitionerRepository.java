package com.clinic.clinic_api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.clinic.clinic_api.model.Practitioner;

public interface PractitionerRepository extends MongoRepository<Practitioner, String> {

    Optional<Practitioner> findByRegistrationNo(String registrationNo);
}
