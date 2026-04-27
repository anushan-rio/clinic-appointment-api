package com.clinic.clinic_api.model;

import jakarta.validation.constraints.NotBlank;

public class Person {

    @NotBlank(message = "Full name is required")
    private String fullName;

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
}
