package com.cognizant.service;


import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.controller.MedicineStockController;

import com.cognizant.model.MedicineStock;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicineStockServiceImplTest {

	@Mock
	MedicineStockController medicinestockcontroller;
	@Mock
	MedicineStockServiceImpl medicinestockservice;

	@Autowired
	MedicineStockServiceImpl medicinestockserviceimpl;

	@Test
	public void testGetMedicineStockInformation() {
		List<MedicineStock> medicineList = medicinestockserviceimpl.getMedicineStockInformation();
		assertNotNull(medicineList);
	}

	
	

	@Test
	public void testGetMedicineByTargetAilment() {
		List<MedicineStock> medicineList1 = medicinestockserviceimpl.getMedicineByTargetAilment("Healthy pharmacy");
		assertNotNull(medicineList1);
	}

	@Test
	public void testGetMedicineByTargetAilmentFail() {
		List<MedicineStock> medicineList = medicinestockserviceimpl.getMedicineByTargetAilment("acy");
		boolean check = medicineList.isEmpty();
		assertTrue(check);
	}

	@Test
	public void testUpdateNumberOfTabletsInStockByName() {
		boolean result = medicinestockserviceimpl.updateNumberOfTabletsInStockByName("Crocin", 240);
		assertTrue(result);
	}
	@SuppressWarnings("unused")
	@Test
	public void testGetNumberOfTabletsInStockByName()
	{
		MedicineStock stock=medicinestockserviceimpl.getNumberOfTabletsInStockByName("Crocin");
	}
	
}
