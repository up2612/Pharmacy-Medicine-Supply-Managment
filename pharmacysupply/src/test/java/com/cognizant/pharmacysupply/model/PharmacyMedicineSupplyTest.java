package com.cognizant.pharmacysupply.model;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PharmacyMedicineSupplyTest {

	@Mock
	PharmacyMedicineSupply supply;

	@Before
	public void Setup() throws Exception {
		supply = new PharmacyMedicineSupply();
		supply.setId(1);
		supply.setMedicinename("Crocin");
		supply.setPharmacyname("Healthy Pharmacy");
		supply.setSupplycount(10);
	}

	@Test
	public void testSetters() {
		supply.setId(1);
		supply.setPharmacyname("Healthy Pharmacy");
		supply.setMedicinename("Orthoherb");
		supply.setSupplycount(10);
		supply.setTranDate("26-06-2021 11:30");
		assertThat(supply.getId().equals(1));
		assertThat(supply.getPharmacyname().equals("Healthy Pharmacy"));
		assertThat(supply.getMedicinename().equals("Orthoherb"));
		assertThat(supply.getSupplycount().equals(10));
		assertThat(supply.getTranDate().equals("26-06-2021 11:30"));
	}

	@Test
	public void testGetters() {
		assertEquals("Crocin", supply.getMedicinename());
	}

	@Test
	public void NoArgsConstructorTest() {
		PharmacyMedicineSupply supply = new PharmacyMedicineSupply();
		assertEquals(null, supply.getMedicinename());
	}

	@Test
	public void AllArgConstTest() {
		PharmacyMedicineSupply supply = new PharmacyMedicineSupply(1,"Healthy Pharmacy","Crocin",50,"26-06-2021 11:30");
		Integer id=1;Integer count =50;
		assertEquals(id, supply.getId());
		assertEquals("Crocin", supply.getMedicinename());
		assertEquals("Healthy Pharmacy", supply.getPharmacyname());
		assertEquals(count, supply.getSupplycount());
		assertEquals("26-06-2021 11:30", supply.getTranDate());
		//asse

	}

//	@Test
//	public void testToString() {
//		assertEquals(
//				"PharmacyMedicineSupply(id=" + supply.getId() + ", pharmacyName=" + supply.getPharmacyname()
//						+ ", medicineName=" + supply.getMedicinename() + ", supplyCount=" + supply.getSupplycount() + ")",
//				supply.toString());
//	}

}
