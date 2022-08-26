package com.cognizant.pharmacysupply.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pharmacysupply.PharmacysupplyApplication;
import com.cognizant.pharmacysupply.exception.MedicineNotFoundException;
import com.cognizant.pharmacysupply.exception.TokenValidationFailedException;
import com.cognizant.pharmacysupply.feignclient.AuthenticationFeignClient;
import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.PharmacyMedicineSupply;
import com.cognizant.pharmacysupply.service.PharmacyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/api/v1")
public class PharmacySupplyController {

	@Autowired
	private PharmacyService pharmacyservice;
	@Autowired
	AuthenticationFeignClient authenticationfeign;
	
	/*
	 *  Method Name --> pharmacySupply
	 *  @param      --> Medicine Demand 
	 *  @return     --> Pharmacy Supply
	 *  This method takes medicine demand as input checks if there is enough stock to 
	 *  fulfill the demand, if sufficient stock is available then it supplies the medicine
	 *  else it will not supply
	 */
	
	@PostMapping("/pharmacy-supply")
	public ResponseEntity<?> getPharmacySupply( @RequestBody List<MedicineDemand> medicineDemandList,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
					throws MedicineNotFoundException,TokenValidationFailedException {
		log.info("medicineDemandList {}:", medicineDemandList);
		if (authenticationfeign.authorizeTheRequest(requestTokenHeader))
		{
		log.info("inside pharmacy supply microservice controller method");
		List<PharmacyMedicineSupply> pharmacySupply = null;
			pharmacySupply = pharmacyservice.getPharmacySupplyCount(medicineDemandList,requestTokenHeader);
			
			if (pharmacySupply == null)  {
				
				log.info("pharmacy supply entity not found");
				
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			
			log.info("returning the pharmacy supply entity");
			return new ResponseEntity<>(pharmacySupply, HttpStatus.OK);
		}
		else
			throw new TokenValidationFailedException("Invalid Token");
	}
	
	@GetMapping("/getmedicinesupply")
	public List<PharmacyMedicineSupply> getMedicineSupply(@RequestHeader(value = "Authorization", required = true)
	String requestTokenHeader) {
		if (authenticationfeign.authorizeTheRequest(requestTokenHeader))
		{
		log.info("inside get medicine supply method");
		List<PharmacyMedicineSupply> medicineSupply = null;
		
		medicineSupply = pharmacyservice.getMedicineSupply();
		log.info("returning medicine supply entity");
		//return new ResponseEntity<>(medicineSupply, HttpStatus.OK);
		return medicineSupply;
		}
		else
			throw new TokenValidationFailedException("Invalid Token");

			
	}

	/*
	 *  Method Name --> getMedicineDemand
	 *  @param      --> Null
	 *  @return     --> Medicine Demanded
	 *  This method returns the medicine demanded till this time.
	 */
	
	@GetMapping("/getmedicinedemand")
	public ResponseEntity<?> getMedicineDemand(@RequestHeader(value = "Authorization", required = true)
	String requestTokenHeader) {
		if (authenticationfeign.authorizeTheRequest(requestTokenHeader))
		{
		
		log.info("inside get medicine demand method");
		List<MedicineDemand> medicineDemand = null;
		medicineDemand = pharmacyservice.getMedicineDemand();
		log.info("returning medicine demand entity");
		return new ResponseEntity<>(medicineDemand, HttpStatus.OK);
		}
		else
			throw new TokenValidationFailedException("Invalid Token");

			
	}

}
