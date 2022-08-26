package com.cognizant.controller;

import java.util.List;






import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.exception.MedicinesByTargetAlimentException;
import com.cognizant.exception.StockAvailabilityException;
import com.cognizant.exception.TokenValidationException;
import com.cognizant.feign.AuthenticationFeignClient;
import com.cognizant.model.MedicineStock;
import com.cognizant.service.MedicineStockServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="/api/v1")
@Slf4j
public class MedicineStockController {
	
	
	@Autowired
	MedicineStockServiceImpl medicinestockservice;
	@Autowired
	AuthenticationFeignClient authorizationfeign;
	
	@GetMapping("/medicinestockinformation")
	public List<MedicineStock> getMedicineInfo(
			@RequestHeader(value="Authorization",required = true)String requestTokenHeader)
					throws StockAvailabilityException,TokenValidationException
	{
		List<MedicineStock> medicineStockList=null;
		if(authorizationfeign.authorizeTheRequest(requestTokenHeader)) {
	    medicineStockList =medicinestockservice.getMedicineStockInformation();
		if(medicineStockList.isEmpty())
				throw new StockAvailabilityException();
		     return medicineStockList;
		}
		else
			throw new TokenValidationException();
		}
		
	


	
	
	
	
   @GetMapping("/targetaliment/{targetaliment}")
	public List<MedicineStock> getMedicinesByTargetAliment(@PathVariable("targetaliment") String targetaliment,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) 
					throws MedicinesByTargetAlimentException  ,TokenValidationException
	{
	   
			if (authorizationfeign.authorizeTheRequest(requestTokenHeader)) {
				List<MedicineStock> medicineStockList = medicinestockservice.getMedicineByTargetAilment(targetaliment);
				if(medicineStockList.isEmpty())
				    throw new MedicinesByTargetAlimentException();
				return medicineStockList;
			} 
			else
				throw new TokenValidationException();


		
	}
   
   
   
   @GetMapping("/nooftabletsinstock/{medicinename}")
	public   MedicineStock getNoOfTabletsInStock(@PathVariable("medicinename") String medicinename,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
					throws TokenValidationException
	{
	   log.info("entered through feign");
	 if (authorizationfeign.authorizeTheRequest(requestTokenHeader)) {
	   MedicineStock medicinestock=medicinestockservice.getNumberOfTabletsInStockByName(medicinename);
	   return medicinestock;
	 }
	 else
		 throw new TokenValidationException();
		 
	}
   
   
   
   @PutMapping("/updatestock/{medicine}/{count}")
	public Boolean updateNumberOfTabletsInStockByName(@PathVariable("medicine") String medicine, 
			@PathVariable("count") int count,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) 
					throws TokenValidationException {
		 Boolean ans = false;

		 if (authorizationfeign.authorizeTheRequest(requestTokenHeader)) {

		 ans =medicinestockservice.updateNumberOfTabletsInStockByName(medicine, count);
		 return ans;
		 }
		 else
			 throw new TokenValidationException();
 

	}
   
   
   
   
   
   
   
   
   

}
