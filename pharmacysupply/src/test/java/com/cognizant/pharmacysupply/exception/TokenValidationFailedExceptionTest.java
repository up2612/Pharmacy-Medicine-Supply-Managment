package com.cognizant.pharmacysupply.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class TokenValidationFailedExceptionTest {

	@Mock
	private TokenValidationFailedException tokenValidationFailedException;

	@Test
	public void testOneArgConstructor() {
		TokenValidationFailedException medicineNotFoundException = new TokenValidationFailedException(
				"Token validation failed.");
		assertEquals("Token validation failed.", medicineNotFoundException.getMessage());
	}
	
//	@Test
//	public void testNoArgsConstructor() {
//		TokenValidationFailedException exception = new TokenValidationFailedException();
//		assertEquals(null, exception.getMessage());
//	}
}
