package com.cognizant.pharmacysupply.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.pharmacysupply.exception.MedicineNotFoundException;
import com.cognizant.pharmacysupply.exception.TokenValidationFailedException;
import com.cognizant.pharmacysupply.feignclient.AuthenticationFeignClient;
import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.PharmacyMedicineSupply;
import com.cognizant.pharmacysupply.service.PharmacyService;
import com.cognizant.pharmacysupply.service.PharmacyServiceImp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration 
@SpringBootTest
public class PharmacySupplyControllerTest {

	@InjectMocks
	PharmacySupplyController pharmacysupplycontroller;

	@Autowired
	PharmacySupplyController supplycontroller;
	
	@Mock
	PharmacyService pharmacyservice;
	
	@Mock
	AuthenticationFeignClient authenticationfeign;
	
	@Mock
	PharmacyServiceImp pharmacyserviceimp;
	
	 String token="Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJVZGRlc2hheSBQYW5kZXkiLCJleHAiOjE2MjQ0NTI4NzYsImlhdCI6MTYyNDQ1MTA3Nn0.ILtmAYw16egRGCwYndHOqFB13S39-_obiefoNykSwvczZEOdcQQ6LBemAh6jZeOuAAcFoSWVrSy0lMKjiO0Iyg";
	
//	@Mock
//	String requestTokenHeader;
	
	@Test
	public void GetPharmacySupplyTest()  {
		List<MedicineDemand> medicineDemandList=new ArrayList<MedicineDemand>();
		MedicineDemand demand=new MedicineDemand(101, "Crocin", 50, "2021-12-26 11:05:05");
		medicineDemandList.add(demand);
		ResponseEntity<?> response=null;
		
		//PharmacyMedicineSupply supply=new PharmacyMedicineSupply(101, "Healthy Pharmacy", "Crocin", 50, "2021-12-26 11:05:05");
		try {
			response =supplycontroller.getPharmacySupply(medicineDemandList, token);
		} catch (TokenValidationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MedicineNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		HttpStatus status=HttpStatus.OK;
		assertEquals(status, response.getStatusCode());
		
	}
	
	@Test
	public void GetPharmacySupplyInvalidTokenTest() throws MedicineNotFoundException  {
		List<MedicineDemand> medicineDemandList=new ArrayList<MedicineDemand>();
		MedicineDemand demand=new MedicineDemand(101, "Crocin", 50, "2021-12-26 11:05:05");
		medicineDemandList.add(demand);
		ResponseEntity<?> response=null;
		//PharmacyMedicineSupply supply=new PharmacyMedicineSupply(101, "Healthy Pharmacy", "Crocin", 50, "2021-12-26 11:05:05");
		try {
			response =pharmacysupplycontroller.getPharmacySupply(medicineDemandList, "token");
		} catch (TokenValidationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	@Test
	public void GetPharmacySupplyMedicineNotFoundExceptionTest()  {
		List<MedicineDemand> medicineDemandList=new ArrayList<MedicineDemand>();
		MedicineDemand demand=new MedicineDemand(101, "hello", 50, "2021-12-26 11:05:05");
		medicineDemandList.add(demand);
		ResponseEntity<?> response = null;
		//PharmacyMedicineSupply supply=new PharmacyMedicineSupply(101, "Healthy Pharmacy", "Crocin", 50, "2021-12-26 11:05:05");
		try {
			response =supplycontroller.getPharmacySupply(medicineDemandList, token);
		} catch (MedicineNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpStatus status=HttpStatus.NOT_FOUND;
		assertEquals(status, response.getStatusCode());
		
	}
	
	@Test
	public void getMedicineSupplyTest() {
		
		
		when(authenticationfeign.authorizeTheRequest("token")).thenReturn(true);
		List<PharmacyMedicineSupply> medicineSupplyList = new ArrayList<PharmacyMedicineSupply>();
		PharmacyMedicineSupply supply=new PharmacyMedicineSupply(101, "Healthy Pharmacy", "Crocin", 50, "2021-12-26 11:05:05");
		 medicineSupplyList.add(supply);
		//ResponseEntity<?> testResponse=new ResponseEntity<>(medicineSupplyList, HttpStatus.OK);
		when(pharmacysupplycontroller.getMedicineSupply("token")).thenReturn(medicineSupplyList);
		List<PharmacyMedicineSupply> medicineSupply=new ArrayList<PharmacyMedicineSupply>();
		medicineSupply=(List<PharmacyMedicineSupply>) pharmacysupplycontroller.getMedicineSupply("token");
		assertEquals(medicineSupply, medicineSupplyList);
	}
	
	@Test
	public void getMedicineDemandTest() throws Exception {
		
		List<MedicineDemand> medicineDemand = new ArrayList<MedicineDemand>();
		when(authenticationfeign.authorizeTheRequest("token")).thenReturn(true);
		ResponseEntity<?> response ;
		response=pharmacysupplycontroller.getMedicineDemand("token");
		HttpStatus status=HttpStatus.OK;
		assertEquals(status, response.getStatusCode());
		//assertEquals(200,HttpStatus.Ok)
		//when(pharmacysupplycontroller.getMedicineDemand("token")).thenReturn(new ResponseEntity<?>(null,HttpStatus.OK));
		
		//ResponseEntit,HttpStatus.Oky<?> responseEntity = pharmacysupplycontroller.getMedicineDemand("requestTokenHeader");
	}
}
