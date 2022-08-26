package com.cognizant.medicalrepresentativeschedulemicroserice.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;


import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class MedicalRepresentativeTest {

	@InjectMocks
	private MedicalRepresentative medicalRepresentative;


	@Before
	public void setup() {
		log.info("Start");
		medicalRepresentative = new MedicalRepresentative();
		medicalRepresentative.setId(1);
		medicalRepresentative.setName("Satwik");
		medicalRepresentative.setPhoneNumber("9456481521");
		log.info("End");
	}


	@Test
	public void testMedicineStockDetails() throws Exception {
		log.info("Start");
		assertEquals(1, medicalRepresentative.getId());
		assertEquals("Satwik", medicalRepresentative.getName());
		assertEquals("9456481521", medicalRepresentative.getPhoneNumber());

		log.info("End");

	}


	@Test
	public void testAllArgsConstructor() {

		MedicalRepresentative medicalRepresentatives = new MedicalRepresentative(1, "R1", "8877779292");
		assertEquals("R1", medicalRepresentatives.getName());
	}


	@Test
	public void testToStringMethod() {
		log.info("Start");
		assertEquals(medicalRepresentative.toString(), medicalRepresentative.toString());
		log.info("End");
	}


	@Test
	public void testSetters() {
		medicalRepresentative.setId(1);
		assertEquals(1, medicalRepresentative.getId());
	}


	@Test
	public void testEqualsMethod() {
		boolean equals = medicalRepresentative.equals(medicalRepresentative);
		assertTrue(equals);
	}

	/**
	 * Tests the hashCode() method
	 */
	@Test
	public void testHashCodeMethod() {
		int hashCode = medicalRepresentative.hashCode();
		assertEquals(hashCode, medicalRepresentative.hashCode());
	}
}
