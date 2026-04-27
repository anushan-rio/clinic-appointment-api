package com.clinic.clinic_api.dto;

import jakarta.validation.constraints.NotBlank;

public class PractitionerRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Registration number is required")
    private String registrationNo;

    @NotBlank(message = "Specialty is required")
    private String specialty;

    // getters & setters

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}