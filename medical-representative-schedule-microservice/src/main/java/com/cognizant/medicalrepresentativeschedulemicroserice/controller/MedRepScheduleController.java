package com.cognizant.medicalrepresentativeschedulemicroserice.controller;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.medicalrepresentativeschedulemicroserice.exception.InvalidDateException;
import com.cognizant.medicalrepresentativeschedulemicroserice.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedulemicroserice.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentativeschedulemicroserice.model.Doctor;
import com.cognizant.medicalrepresentativeschedulemicroserice.model.MedicalRepresentative;
import com.cognizant.medicalrepresentativeschedulemicroserice.model.RepSchedule;
import com.cognizant.medicalrepresentativeschedulemicroserice.repository.DoctorRepository;
import com.cognizant.medicalrepresentativeschedulemicroserice.service.MedRepScheduleService;
import com.cognizant.medicalrepresentativeschedulemicroserice.service.MedRepService;
import com.cognizant.medicalrepresentativeschedulemicroserice.util.DateUtil;

//import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/api/v1")
public class MedRepScheduleController {

	@Autowired
	private MedRepScheduleService scheduleService;

	@Autowired
	private MedRepService medicalRepresentativeService;

	@Autowired
	private DoctorRepository doctorRepositry;
	@Autowired
	AuthenticationFeignClient authenticationfeign;
	
	/*
	 *  Method Name --> repschedule
	 *  @param      --> schedulestartdate
	 *  @return     --> rep schedule
	 *  This method takes schedule start date as input checks if give date is valid, 
	 *  if date is valid then it returns the schedule details
	 *  else it will return empty list.
	 */
	

	@GetMapping("/repschedule/{schedulestartdate}")
	public ResponseEntity<List<RepSchedule>> getRepSchedule(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@PathVariable("schedulestartdate") final String schedulestartdate)
			throws InvalidDateException, TokenValidationFailedException {
		System.out.println(requestTokenHeader);
		log.info("enterd in RepSchedule entry point");
		log.debug("scheduleStartDate : {}", schedulestartdate);

		List<RepSchedule> repSchedule = null;
		if (authenticationfeign.authorizeTheRequest(requestTokenHeader)) {

			LocalDate localDate = DateUtil.getDate(schedulestartdate);
			log.debug("localDate : {}", localDate);

			try {

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
				localDate = LocalDate.parse(schedulestartdate, formatter);

			} catch (Exception e) {
				log.error("date format exception");
				throw new InvalidDateException();
			}

			repSchedule = scheduleService.getRepSchedule( requestTokenHeader,localDate);

			log.debug("repSchedule : {}", repSchedule);
			log.info("exited in RepSchedule entry point");

			return ResponseEntity.of(Optional.of(repSchedule));
		} else {
			log.error("token validation failed");
			throw new TokenValidationFailedException();
		}
		
	}
	
	/*
	 *  Method Name --> schedulerepository
	 *  @param      --> null
	 *  @return     --> schedulerepository
	 *  This method returns the medical representatives details.
	 */
	

	@GetMapping("/schedulerepository")
	public List<MedicalRepresentative> getMedicalRepresentatives(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws TokenValidationFailedException {
		log.info("enterd in schedulerepository entry point");
		List<MedicalRepresentative> medicalRepresentatives = null;
		if (authenticationfeign.authorizeTheRequest(requestTokenHeader)) {
			medicalRepresentatives = medicalRepresentativeService.getMedicalRepresentatives();
			log.info("exited in schedulerepository entry point");
			return medicalRepresentatives;
		} else {
			log.error("token validation failed");
			throw new TokenValidationFailedException();
		}
	}
	
	/*
	 *  Method Name --> doctors
	 *  @param      --> null
	 *  @return     --> doctors
	 *  This method returns the doctors details.
	 */
	

	@GetMapping("/doctors")
	public List<Doctor> getDoctors(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws TokenValidationFailedException {
		log.info("enterd in doctors entry point");
		List<Doctor> doctors = null;
		if (authenticationfeign.authorizeTheRequest(requestTokenHeader)) {

			doctors = doctorRepositry.findAll();

			log.info("exited in doctors entry point");
			return doctors;
		} else{
			log.error("token validation failed");
			throw new TokenValidationFailedException();
		}
	}

}
