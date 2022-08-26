package com.cognizant.medicalrepresentativeschedulemicroserice.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class InvalidDateExceptionTest {
	@Mock
	private InvalidDateException dateNotFound;

//	@Test
//	public void testOneArgConstructor() {
//		InvalidDateException exception = new InvalidDateException();
//		assertEquals("Invalid date.", exception.getMessage());
//	}

	@Test
	public void testNoArgConstructor() {
		InvalidDateException exception = new InvalidDateException();
		assertEquals(null, exception.getMessage());
	}

}
