package com.cognizant.medicalrepresentativeschedulemicroserice.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

public class TestDateUtil {
	
	@Autowired
	public DateUtil dateutil;
	
	@Test
	public void getDateTest() {
		LocalDate ld = dateutil.getDate("2021-06-30");
		assertNotNull(ld);
	}

}
