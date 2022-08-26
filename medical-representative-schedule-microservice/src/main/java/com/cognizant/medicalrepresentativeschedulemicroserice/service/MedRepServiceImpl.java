package com.cognizant.medicalrepresentativeschedulemicroserice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicalrepresentativeschedulemicroserice.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedulemicroserice.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentativeschedulemicroserice.model.MedicalRepresentative;
import com.cognizant.medicalrepresentativeschedulemicroserice.repository.MedRepRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedRepServiceImpl implements MedRepService {

	@Autowired
	AuthenticationFeignClient authFeignClient;
	
	@Autowired
	private MedRepRepository medicalRepresentativesRepository;

	@Override
	public List<MedicalRepresentative>	 getMedicalRepresentatives() {
		
		return medicalRepresentativesRepository.findAll();
	}
	
	
}
