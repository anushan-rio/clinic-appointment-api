package com.clinic.clinic_api.mapper;

import com.clinic.clinic_api.dto.PractitionerRequest;
import com.clinic.clinic_api.dto.PractitionerResponse;
import com.clinic.clinic_api.model.Practitioner;

public class PractitionerMapper {

    public static Practitioner toEntity(PractitionerRequest dto) {
        Practitioner p = new Practitioner();
        p.setFullName(dto.getFullName());
        p.setRegistrationNo(dto.getRegistrationNo());
        p.setSpecialty(dto.getSpecialty());
        return p;
    }

    public static PractitionerResponse toResponse(Practitioner p) {
        PractitionerResponse res = new PractitionerResponse();
        res.setId(p.getId());
        res.setFullName(p.getFullName());
        res.setRegistrationNo(p.getRegistrationNo());
        res.setSpecialty(p.getSpecialty());
        return res;
    }
}