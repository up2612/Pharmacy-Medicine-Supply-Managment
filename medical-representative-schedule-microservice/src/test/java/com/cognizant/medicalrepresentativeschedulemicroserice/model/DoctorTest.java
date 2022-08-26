package com.cognizant.medicalrepresentativeschedulemicroserice.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import org.springframework.boot.test.context.SpringBootTest;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class DoctorTest {

	@InjectMocks
	public Doctor doctor;
	
//
//	@BeforeEach
//	public void setup() {
//		log.info("Start");
//		doctor = new Doctor();
//		doctor.setName("Varsha");
//		doctor.setPhoneNumber("9854125421");
//		doctor.setTreatingAilment("General");
//
//		log.info("End");
//	}
	
	@Test
	public void testsetter() {
		doctor = new Doctor();
		doctor.setName("Varsha");
		doctor.setPhoneNumber("9854125421");
		doctor.setTreatingAilment("General");
		 assertThat(doctor.getName().equals("varsha"));
		 assertThat(doctor.getPhoneNumber().equals("9854125421"));
		 assertThat(doctor.getTreatingAilment().equals("General"));
		
	}
	

	@Test
	public void testMedicineStockDetails() throws Exception {
		log.info("Start");
		doctor = new Doctor(1, "varsha", "9854125421", "General");
		assertThat(doctor.getName().equals("varsha"));
		 assertThat(doctor.getPhoneNumber().equals("9854125421"));
		 assertThat(doctor.getTreatingAilment().equals("General"));

		log.info("End");
	}

	@Test
	public void testAllArgsConstructor() {

		Doctor doctor = new Doctor(1, "D1", "8877779292", "General");
		assertEquals("D1", doctor.getName());
	}
	
	@Test
	public void testNoArgsConstructor() {

		Doctor doctor = new Doctor();
		//assertEquals("D1", doctor.getName());
	}


//	@Test
//	public void testSetters() {
//		doctor.setName("D1");
//		assertEquals("D1", doctor.getName());
//	}

//	@Test
//	public void testEqualsMethod() {
//		boolean equals = doctor.equals(doctor);
//		assertTrue(equals);
//	}

//	@Test
//	public void testHashCodeMethod() {
//		doctor = new Doctor(1, "varsha", "9854125421", "General");
//		int hashCode = doctor.hashCode();
//		assertEquals(hashCode, doctor.hashCode());
//	}

}
