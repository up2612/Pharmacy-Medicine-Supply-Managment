package com.cognizant.pharmacysupply.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MedicineStockTest {

	@Mock
	MedicineStock medicineStock;

	@Before
	public void setUp() throws Exception {
		medicineStock = new MedicineStock(1,"Crocin","digoxin","Cardiac Arrest","Healthy Pharmacy",new Date(2022 - 9 - 12),10);
	}

	@Test
	public void testSetters() {
		Date d=new Date(2022 - 9 - 12);
		Integer tablets_stock=10;
		assertThat(medicineStock.getId().equals(1));
		assertThat(medicineStock.getChemicalComposition().equals("digoxin"));
		assertThat(medicineStock.getPharmacyName().equals("Healthy Pharmacy"));
		assertThat(medicineStock.getTargetAliment().equals("Cardiac Arrest"));
		assertThat(medicineStock.getDateOfExpiry().equals(d));
		assertThat(medicineStock.getNumberOfTabletsInStock().equals(tablets_stock));
	}

	@Test
	public void testGetters() {
		Integer id=1,tablets_stock=10;
		Date dy=new Date(2022 - 9 - 12);
		assertEquals(id, medicineStock.getId());
		assertEquals("digoxin", medicineStock.getChemicalComposition());
		assertEquals("Healthy Pharmacy", medicineStock.getPharmacyName());
		assertEquals("Cardiac Arrest", medicineStock.getTargetAliment());
		assertEquals(dy, medicineStock.getDateOfExpiry());
		assertEquals(tablets_stock, medicineStock.getNumberOfTabletsInStock());
		
	}

	@Test
	public void NoArgsConstructorTest() {
		MedicineStock medicineStock = new MedicineStock();
		assertEquals(null, medicineStock.getPharmacyName());
	}

	@Test
	public void AllArgConstTest() {
		MedicineStock stock = new MedicineStock(1, "Crocin", "digoxin", "General", "Healthy Pharmacy",
				new Date(2022 - 9 - 12), 10);
		assertEquals("Crocin", stock.getName());
		assertEquals(medicineStock.getNumberOfTabletsInStock(), stock.getNumberOfTabletsInStock());
	}

//	@Test
//	public void testToString() {
//		assertEquals("MedicineStock(id=" + 1 + ", name=" + medicineStock.getName() + ", chemicalComposition=" + medicineStock.getChemicalComposition()
//				+ ", targetAliment=" + medicineStock.getTargetAliment() + ", pharmacyName=" + medicineStock.getPharmacyName() + ", dateOfExpiry="
//				+ medicineStock.getDateOfExpiry() + ", numberOfTabletsInStock=" + medicineStock.getNumberOfTabletsInStock() + ")", medicineStock.toString());
//	}

}
