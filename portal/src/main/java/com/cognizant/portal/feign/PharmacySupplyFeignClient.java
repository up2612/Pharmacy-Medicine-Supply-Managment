package com.cognizant.portal.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cognizant.portal.model.MedicineDemand;
import com.cognizant.portal.model.MedicineSupply;


@FeignClient(url = "${pharmaysupply.url}", name = "pharmacy-supply-service")
public interface PharmacySupplyFeignClient {


	@PostMapping("/api/v1/pharmacy-supply")
	public ResponseEntity<?> getPharmacySupply(
			@RequestBody List<MedicineDemand> medicineDemandList,
			@RequestHeader(name = "Authorization") String token);


	@GetMapping("/api/v1/getmedicinesupply")
	public List<MedicineSupply> getMedicineSupply(@RequestHeader(name = "Authorization") String token);

	@GetMapping("/api/v1/getmedicinedemand")
	public ResponseEntity<?> getMedicineDemand(@RequestHeader(name = "Authorization") String token);

}
