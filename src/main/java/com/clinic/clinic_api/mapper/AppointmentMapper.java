package com.clinic.clinic_api.mapper;

import java.time.LocalDateTime;

import com.clinic.clinic_api.dto.AppointmentRequest;
import com.clinic.clinic_api.dto.AppointmentResponse;
import com.clinic.clinic_api.model.Appointment;

public class AppointmentMapper {
	 public static Appointment toEntity(AppointmentRequest dto) {
	        Appointment a = new Appointment();
	        a.setPatientId(dto.getPatientId());
	        a.setPractitionerId(dto.getPractitionerId());
	        a.setStartTime(dto.getStartTime());
	        a.setEndTime(dto.getEndTime());
	        a.setNotes(dto.getNotes());
	        a.setStatus("BOOKED");
	        a.setCreatedAt(LocalDateTime.now());
	        return a;
	    }

	    public static AppointmentResponse toResponse(Appointment a) {
	        AppointmentResponse res = new AppointmentResponse();
	        res.setId(a.getId());
	        res.setPatientId(a.getPatientId());
	        res.setPractitionerId(a.getPractitionerId());
	        res.setStartTime(a.getStartTime());
	        res.setEndTime(a.getEndTime());
	        res.setStatus(a.getStatus());
	        res.setNotes(a.getNotes());
	        return res;
}
}
