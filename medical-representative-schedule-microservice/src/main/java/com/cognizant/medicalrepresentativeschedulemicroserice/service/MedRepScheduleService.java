package com.cognizant.medicalrepresentativeschedulemicroserice.service;

import java.time.LocalDate;
import java.util.List;

import com.cognizant.medicalrepresentativeschedulemicroserice.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedulemicroserice.model.RepSchedule;

public interface MedRepScheduleService {
	public List<RepSchedule> getRepSchedule(
			String token,  LocalDate scheduleStartDate) /* throws TokenValidationFailedException */;

}
