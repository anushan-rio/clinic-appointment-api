package com.clinic.clinic_api.mapper;

import com.clinic.clinic_api.dto.PatientRequest;
import com.clinic.clinic_api.dto.PatientResponse;
import com.clinic.clinic_api.model.Patient;

public class PatientMapper {

    public static Patient toEntity(PatientRequest dto) {
        Patient p = new Patient();
        p.setFullName(dto.getFullName());
        p.setNationalId(dto.getNationalId());
        p.setDateOfBirth(dto.getDateOfBirth());
        p.setEmail(dto.getEmail());
        p.setPhone(dto.getPhone());
        return p;
    }

    public static PatientResponse toResponse(Patient p) {
        PatientResponse res = new PatientResponse();
        res.setId(p.getId());
        res.setFullName(p.getFullName());
        res.setNationalId(p.getNationalId());
        res.setDateOfBirth(p.getDateOfBirth());
        res.setEmail(p.getEmail());
        res.setPhone(p.getPhone());
        return res;
    }
}