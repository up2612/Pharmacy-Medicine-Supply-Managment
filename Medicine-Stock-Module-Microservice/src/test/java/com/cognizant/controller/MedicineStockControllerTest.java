package com.cognizant.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.exception.MedicinesByTargetAlimentException;
import com.cognizant.exception.StockAvailabilityException;
import com.cognizant.exception.TokenValidationException;
import com.cognizant.feign.AuthenticationFeignClient;
import com.cognizant.model.MedicineStock;
import com.cognizant.service.MedicineStockServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicineStockControllerTest {

	
	@Mock
	MedicineStock medicnestock;
	@InjectMocks
	MedicineStockController medicinestockcontroller;
	@Mock
	MedicineStockServiceImpl medicinestockservice;
	@Mock
	AuthenticationFeignClient authorizationfeign;

	@Test
	public void getMedicineInfoTest() throws TokenValidationException {
		when(authorizationfeign.authorizeTheRequest("token")).thenReturn(true);
		List<MedicineStock> medicineStockList = new ArrayList<>();
		MedicineStock medicinestock = new MedicineStock(101, "Crocin",
				"disaccharide gentiobiose, dicarboxylic acid crocetin", "General", "Healthy Pharmacy",
				new java.util.Date(2022 - 9 - 12), 200);
		medicineStockList.add(medicinestock);
		when(medicinestockservice.getMedicineStockInformation()).thenReturn(medicineStockList);
		try {
			List<MedicineStock> list = medicinestockcontroller.getMedicineInfo("token");
			assertEquals(list, medicineStockList);
		} catch (StockAvailabilityException e) {
			e.printStackTrace();
		} 
		

	}
	@SuppressWarnings("unused")
	@Test
	public void getMedicineInfoFalseTokenTest() throws StockAvailabilityException  {
		when(authorizationfeign.authorizeTheRequest("token")).thenReturn(false);
		List<MedicineStock> medicineStockList = new ArrayList<>();
		MedicineStock medicinestock = new MedicineStock(101, "Crocin",
				"disaccharide gentiobiose, dicarboxylic acid crocetin", "General", "Healthy Pharmacy",
				new java.util.Date(2022 - 9 - 12), 200);
		medicineStockList.add(medicinestock);
		when(medicinestockservice.getMedicineStockInformation()).thenReturn(medicineStockList);
		
			List<MedicineStock> list=new ArrayList<>();
			try {
				list = medicinestockcontroller.getMedicineInfo("token");
			}  catch (TokenValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}

	@Test
	public void getMedicineInfoEmptyTest() throws TokenValidationException  {
		when(authorizationfeign.authorizeTheRequest("token")).thenReturn(true);
		List<MedicineStock> medicineStockList = new ArrayList<>();
		when(medicinestockservice.getMedicineStockInformation()).thenReturn(medicineStockList);
		
			List<MedicineStock> list=new ArrayList<>();
			try {
				list = medicinestockcontroller.getMedicineInfo("token");
			} catch (StockAvailabilityException e) {
				e.printStackTrace();
			} 
			assertEquals(list.isEmpty(), true);
		

	}
	@Test
	public void getMedicinesByTargetAlimentTest() throws MedicinesByTargetAlimentException, TokenValidationException {
		when(authorizationfeign.authorizeTheRequest("token")).thenReturn(true);
		List<MedicineStock> medicineStockList = new ArrayList<>();
		MedicineStock medicinestock = new MedicineStock(101, "Crocin",
				"disaccharide gentiobiose, dicarboxylic acid crocetin", "General", "Healthy Pharmacy",
				new java.util.Date(2022 - 9 - 12), 200);
		medicineStockList.add(medicinestock);
		when(medicinestockservice.getMedicineByTargetAilment("General")).thenReturn(medicineStockList);
			List<MedicineStock> list = medicinestockcontroller.getMedicinesByTargetAliment("General", "token");
			assertEquals(list, medicineStockList);
		

	}
	@SuppressWarnings("unused")
	@Test
	public void getMedicinesByTargetAlimentFalseTokenTest() throws MedicinesByTargetAlimentException {
		when(authorizationfeign.authorizeTheRequest("token")).thenReturn(false);
		List<MedicineStock> medicineStockList = new ArrayList<>();
		MedicineStock medicinestock = new MedicineStock(101, "Crocin",
				"disaccharide gentiobiose, dicarboxylic acid crocetin", "General", "Healthy Pharmacy",
				new java.util.Date(2022 - 9 - 12), 200);
		medicineStockList.add(medicinestock);
		when(medicinestockservice.getMedicineByTargetAilment("General")).thenReturn(medicineStockList);
		try {
			List<MedicineStock> list = medicinestockcontroller.getMedicinesByTargetAliment("General", "token");
		} catch (TokenValidationException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	@Test
	public void getMedicinesByTargetAlimentTestEmptyTest() throws TokenValidationException {
		when(authorizationfeign.authorizeTheRequest("token")).thenReturn(true);
		List<MedicineStock> medicineStockList = new ArrayList<>();
		
		when(medicinestockservice.getMedicineByTargetAilment("General")).thenReturn(medicineStockList);
		try {
			List<MedicineStock> list = medicinestockcontroller.getMedicinesByTargetAliment("General", "token");
			assertEquals(medicineStockList.isEmpty(), true);
		} catch (MedicinesByTargetAlimentException e) {
			e.printStackTrace();
		} 

	}
	
	@Test
	public void getNoOfTabletsInStockTest() throws  TokenValidationException {
		when(authorizationfeign.authorizeTheRequest("token")).thenReturn(true);
		MedicineStock medicinestock = new MedicineStock(101, "Crocin",
				"disaccharide gentiobiose, dicarboxylic acid crocetin", "General", "Healthy Pharmacy",
				new java.util.Date(2022 - 9 - 12), 200);
		when(medicinestockservice.getNumberOfTabletsInStockByName("Crocin")).thenReturn(medicinestock);
			MedicineStock count = medicinestockcontroller.getNoOfTabletsInStock("Crocin", "token");
			assertEquals(count.getNumberOfTabletsInStock(), 200);
		

	}
	@SuppressWarnings("unused")
	@Test
	public void getNoOfTabletsInStockFalseTokenTest()  {
		when(authorizationfeign.authorizeTheRequest("token")).thenReturn(false);
		MedicineStock medicinestock = new MedicineStock(101, "Crocin",
				"disaccharide gentiobiose, dicarboxylic acid crocetin", "General", "Healthy Pharmacy",
				new java.util.Date(2022 - 9 - 12), 200);
		when(medicinestockservice.getNumberOfTabletsInStockByName("Crocin")).thenReturn(medicinestock);

			MedicineStock count=new MedicineStock();
			try {

				count = medicinestockcontroller.getNoOfTabletsInStock("Crocin", "token");
			} catch (TokenValidationException e) {
				e.printStackTrace();
			}
		

	}
	
	
	@Test
	public void getNoOfTabletsInStockEmptyTest() {
		when(authorizationfeign.authorizeTheRequest("token")).thenReturn(true);
		MedicineStock medicinestock=new MedicineStock(null,"","",""," ",null,null);
		when(medicinestockservice.getNumberOfTabletsInStockByName("Croc")).thenReturn(medicinestock);
		try {
			MedicineStock medicine = medicinestockcontroller.getNoOfTabletsInStock("Croc", "token");
			assertEquals(medicine, medicinestock);
		} 	catch (TokenValidationException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void updateNumberOfTabletsInStockByNameTrueTest() throws TokenValidationException {
		when(authorizationfeign.authorizeTheRequest("token")).thenReturn(true);
		when(medicinestockservice.updateNumberOfTabletsInStockByName("Crocin", 50)).thenReturn(true);
		Boolean result = medicinestockcontroller.updateNumberOfTabletsInStockByName("Crocin", 50, "token");
	    assertEquals(result,true);
		

	}
	@Test
	public void updateNumberOfTabletsInStockByNameFalseTokenTest()  {
		
		when(authorizationfeign.authorizeTheRequest("token")).thenReturn(false);


		
			try {
				//assertEquals(result,true);
				when(medicinestockservice.updateNumberOfTabletsInStockByName("Crocin", 50)).thenReturn(true);
				medicinestockcontroller.updateNumberOfTabletsInStockByName("Crocin", 50, "token");


			} catch (TokenValidationException e) {
				e.printStackTrace();
			}
			
		

	}


	@Test
	public void updateNumberOfTabletsInStockByNameFalseTest()  {
		when(authorizationfeign.authorizeTheRequest("token")).thenReturn(true);
		
		when(medicinestockservice.updateNumberOfTabletsInStockByName("Crocin", 50)).thenReturn(false);
		
			Boolean result=false;
			try {
				result = medicinestockcontroller.updateNumberOfTabletsInStockByName("Invalid", 50, "token");
			} catch (TokenValidationException e) {
				e.printStackTrace();
			}
			assertEquals(result,false);
		

	}

	
}
