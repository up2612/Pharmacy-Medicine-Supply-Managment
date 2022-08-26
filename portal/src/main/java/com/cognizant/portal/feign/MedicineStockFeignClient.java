package com.cognizant.portal.feign;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.portal.model.MedicineStock;


@FeignClient(url = "${medicinestock.url}", name = "MedicineStock-MicroService")
public interface MedicineStockFeignClient {

	@GetMapping("api/v1/targetaliment/{targetaliment}")
	public List<MedicineStock> getMedicineByTreatingAilment(
			@PathVariable("treatingAilment") String treatingAilment,
			@RequestHeader("Authorization") String token);

	@GetMapping("api/v1/medicinestockinformation")
	public ResponseEntity<?> getMedicineStockInformation(@RequestHeader(name = "Authorization") String token);
	
	@GetMapping("api/v1/nooftabletsinstock/{medicinename}")
	public MedicineStock getNoOfTabletsInStock(@PathVariable("medicinename") String medicinename,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader);
}
