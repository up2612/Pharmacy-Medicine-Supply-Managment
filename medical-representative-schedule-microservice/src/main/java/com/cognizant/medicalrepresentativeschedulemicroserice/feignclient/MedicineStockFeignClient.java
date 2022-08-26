package com.cognizant.medicalrepresentativeschedulemicroserice.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.medicalrepresentativeschedulemicroserice.model.MedicineStock;

@FeignClient(name="MedicineStock-MicroService",url="${medicinestock.url}")
public interface MedicineStockFeignClient {

	@GetMapping("api/v1/targetaliment/{targetaliment}")
	public List<MedicineStock>getMedicinesByTreatingAilment( @PathVariable("targetaliment") String targetaliment,@RequestHeader("Authorization") String token);

}
