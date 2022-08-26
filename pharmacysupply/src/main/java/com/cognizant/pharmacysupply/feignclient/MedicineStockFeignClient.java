package com.cognizant.pharmacysupply.feignclient;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.pharmacysupply.model.MedicineStock;

@FeignClient(name="MedicineStock-MicroService",url="${medicinestock.url}")
public interface MedicineStockFeignClient {

	@GetMapping("api/v1/nooftabletsinstock/{medicine}")
	public MedicineStock getNumberOfTabletsInStock(@PathVariable("medicine") String medicine,@RequestHeader(name = "Authorization") String token);
	
	@PutMapping("api/v1/updatestock/{medicine}/{count}")
	public Boolean updateNumberOfTabletsInStockByName(@PathVariable("medicine") String medicine, @PathVariable("count") int count,@RequestHeader(name = "Authorization") String token);
}
