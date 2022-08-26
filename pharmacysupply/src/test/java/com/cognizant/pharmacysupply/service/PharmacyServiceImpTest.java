package com.cognizant.pharmacysupply.service;

import static org.junit.Assert.assertNotNull;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.pharmacysupply.exception.MedicineNotFoundException;
import com.cognizant.pharmacysupply.feignclient.MedicineStockFeignClient;
import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.MedicineStock;
import com.cognizant.pharmacysupply.model.PharmacyMedicineSupply;
import com.cognizant.pharmacysupply.model.PharmacyMedicineSupplyTest;
import com.cognizant.pharmacysupply.repository.MedicineDemandRepository;
import com.cognizant.pharmacysupply.repository.PharmacyMedicineSupplyRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class PharmacyServiceImpTest {

	@InjectMocks
	PharmacyServiceImp pharmacyservice;
	
//	@MockBean
//	PharmacyServiceImp pharmacyservice;
	
//	@MockBean
//	PharmacyServiceImp service;

	PharmacyServiceImp service=new PharmacyServiceImp();
	
	@Mock
	MedicineDemand medicineDemand;
	
	@Mock
	MedicineStock medicineStock;
	
	
	@Mock
	PharmacyMedicineSupplyRepository supplyrepository;
	
	@Mock
	MedicineDemandRepository demandrepository;
	
	@Mock
	MedicineStockFeignClient medicinestockfeignclient;
	
	@Mock
	PharmacyMedicineSupply supply;
	
//	@Autowired
//	PharmacyServiceImp service;
	
	
	List<MedicineDemand> medicineDemandList = new ArrayList<>();
	String dummytoken,realtoken;
	MedicineDemand demand=new MedicineDemand(1, "Crocin", 200, "2021/06/21 13:01:01");
	MedicineStock stock;
	List<PharmacyMedicineSupply > listmedicinesupplie= new ArrayList<PharmacyMedicineSupply>();
	PharmacyMedicineSupply medicinesupply;
	
//	@BeforeEach
//	public void init() {
//		demand=new MedicineDemand(1, "Crocin", 200, "2021/06/21 13:01:01");
//		medicineDemandList.add(demand);
//		dummytoken="dummy";
//		realtoken="token";
//		medicinesupply = new PharmacyMedicineSupply(101, "Healthy Pharamcy", "Crocin", 10, "2021/06/25");
//		listmedicinesupplie.add(medicinesupply);
//		Date doe=new Date(2022-06-16);
//		stock=new MedicineStock(101,"Crocin", "disaccharide gentiobiose, dicarboxylic acid crocetin", "General", "Healthy Pharmacy",doe, 200);
//		
//	}
	
	@Test
	public void GetPharmacySupplyCountTest() throws MedicineNotFoundException{
		

		when(pharmacyservice.getPharmacySupplyCount(medicineDemandList, realtoken)).thenReturn(listmedicinesupplie);
		
	}
	
//	@Test
//	public void SetSupplyTest() throws MedicineNotFoundException {
//		String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDaGlyYW5qZWV2aSIsImV4cCI6MTYyNDM2OTgxNSwiaWF0IjoxNjI0MzY4MDE1fQ.6Gq8ByKPeFT8k43NS9is0I9LK3CR1U8yf1bExaJFpaaQzeh264dMZpneMhBkbkXQlHMe47BuB3pFzGuyJMQSgA";
//		
//			when(pharmacyservice.setSupply(medicinesupply, demand, stock, token)).thenReturn(true);
//		
//	}
	
	@Test
	public void GetNumberOfTabletsTest() {
		
		//when(pharmacyservice.getNumberOfTablets(demand, realtoken)).thenReturn(stock)
//		String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDaGlyYW5qZWV2aSIsImV4cCI6MTYyNDM3MDYxNywiaWF0IjoxNjI0MzY4ODE3fQ.L_iPjH8uUuuPHq697lJNtc_cYE8iwSD4jeAUkdozUykpJfEmvSxNrS8vBxEb--9rMUOxMcvF6VmD-MmZwf_UFw";
//		when(pharmacyservice.getNumberOfTablets(demand, token)).thenReturn(stock);
//		when(service.getNumberOfTablets(demand, token)).thenReturn(stock);
		
//		
		MedicineStock medicineStock=new MedicineStock();
		medicineStock=null;
		when(medicinestockfeignclient.getNumberOfTabletsInStock(medicineDemand.getMedicinename(),"token")).thenReturn(medicineStock);
		try {
			medicineStock=pharmacyservice.getNumberOfTablets(medicineDemand,"token");
			assertNull(medicineStock);
		} catch (MedicineNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void GetMedicineDemandTest() {
		
		List<MedicineDemand> medicinedemandlist=null;
		medicinedemandlist=pharmacyservice.getMedicineDemand();
		assertNotNull(medicinedemandlist);
	}
	
	@Test
	public void GetMedicineSupplyTest() {
		
		List<PharmacyMedicineSupply> pharmacyMedicineSupplies = pharmacyservice.getMedicineSupply();
		assertNotNull(pharmacyMedicineSupplies);
	}
}
