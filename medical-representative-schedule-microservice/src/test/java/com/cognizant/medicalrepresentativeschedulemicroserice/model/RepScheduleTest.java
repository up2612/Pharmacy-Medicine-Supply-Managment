package com.cognizant.medicalrepresentativeschedulemicroserice.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class RepScheduleTest {

	@InjectMocks
	private RepSchedule repSchedule;
	
	@Before
	public void setup() {
		
		repSchedule = new RepSchedule();
		repSchedule.setId(2);
		repSchedule.setDoctorName("Varsha");
		repSchedule.setDoctorContactNumber("9854125421");
		repSchedule.setMeetingDate(LocalDate.now());
		repSchedule.setMeetingSlot("1 PM to 2 PM");
		repSchedule.setTreatingAilment("General");
	
		//repSchedule.setRepName(repName);
	}
	
	@Test
	public void testMedicineStockDetails() throws Exception {
		log.info("Start");
		assertEquals("Varsha", repSchedule.getDoctorName());
		assertEquals("9854125421", repSchedule.getDoctorContactNumber());
		assertEquals(LocalDate.now(), repSchedule.getMeetingDate());
		assertEquals("1 PM to 2 PM",repSchedule.getMeetingSlot());
		assertEquals("General", repSchedule.getTreatingAilment());

		log.info("End");
	}

	@Test
	public void testAllArgsConstructor() {
		String[] medicines = { "Crocin", "Percocet" };

		RepSchedule repSchedule = new RepSchedule(2,"kishore","Varsha","9854125421",LocalDate.now(), "1 PM to 2 PM",medicines, "General");
		assertEquals("Varsha", repSchedule.getDoctorName());
	}

	@Test
	public void testToStringMethod() {
		log.info("Start");
		assertEquals(repSchedule.toString(), repSchedule.toString());
		log.info("End");
	}

	@Test
	public void testSetters() {
		repSchedule.setDoctorName("D1");
		assertEquals("D1", repSchedule.getDoctorName());
	}

	@Test
	public void testEqualsMethod() {
		boolean equals = repSchedule.equals(repSchedule);
		assertTrue(equals);
	}

	@Test
	public void testHashCodeMethod() {
		int hashCode = repSchedule.hashCode();
		assertEquals(hashCode, repSchedule.hashCode());
	}

}
