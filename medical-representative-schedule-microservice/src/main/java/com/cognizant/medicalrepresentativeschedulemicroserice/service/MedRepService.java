package com.cognizant.medicalrepresentativeschedulemicroserice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.medicalrepresentativeschedulemicroserice.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedulemicroserice.model.MedicalRepresentative;

public interface MedRepService {
	public List<MedicalRepresentative> getMedicalRepresentatives(/*String token*/) /*throws TokenValidationFailedException*/;
}
