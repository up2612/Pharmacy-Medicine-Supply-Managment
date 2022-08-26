package com.cognizant.medicalrepresentativeschedulemicroserice.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MedicineStockTest {
	@InjectMocks
	MedicineStock medicinestockk;
	private MedicineStock medicinestock=new MedicineStock(101,"Crocin","disaccharide gentiobiose, dicarboxylic acid crocetin","General","Healthy Pharmacy",new java.util.Date(2022 - 9 - 12),200);
	@Test
	public void testSetterId() {
		medicinestock.setId(101);
		assertThat(Integer.valueOf(medicinestock.getId()).equals(Integer.valueOf(101)));
	}
	@Test
	public void testSetterName()
	{   		medicinestock.setName("Crocin");
		assertThat(medicinestock.getName().equals("Crocin"));
	}
	@Test
	public	void testSetterChemicalComposition()
	{
		medicinestock.setChemicalComposition("disaccharide gentiobiose, dicarboxylic acid crocetin");
		assertThat(medicinestock.getChemicalComposition().equals("disaccharide gentiobiose, dicarboxylic acid crocetin"));
	}
	@Test
	public void testSetterTargetAliment()
	{      
		medicinestock.setTargetAliment("General");
		assertThat(medicinestock.getTargetAliment().equals("General"));
	}
	@Test
	public void testSetterNumberOfTabletsInStock()
	{
		medicinestock.setNumberOfTabletsInStock(200);
		assertThat(medicinestock.getNumberOfTabletsInStock().equals(200));
	}
	@Test
	public void testSetterForDate()
	{
		medicinestock.setDateOfExpiry(new java.util.Date(2022 - 9 - 12));
		assertThat(medicinestock.getDateOfExpiry().equals("2022-9-12"));
	}
	@Test
	public void testSetterForPharmacyName()
	{
		medicinestock.setPharmacyName("Healthy Pharmacy");
		assertThat(medicinestock.getPharmacyName().equals("Healthy Pharmacy"));
	}
	
	@Test
	public void testNoArgs() {
		assertThat(new MedicineStock()).isNotNull();
	}
	@Test
	public void testAllArgs() {
		assertThat(assertThat(new MedicineStock(1001,"Crocin","disaccharide gentiobiose, dicarboxylic acid crocetin","General","Healthy Pharmacy",new java.util.Date(2022 - 9 - 12),200))).isNotNull();
	}
	
	
}