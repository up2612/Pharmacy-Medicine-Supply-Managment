package com.cognizant.medicalrepresentativeschedulemicroserice.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.medicalrepresentativeschedulemicroserice.model.ErrorResponse;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GlobalExceptionTest {
	
	@Autowired
	public GlobalException globalexception;
	
	
	@Test
	public void testhandleAllException()
	{
            Exception e=new Exception("Invalid");
		ResponseEntity<ErrorResponse> error=globalexception.handleAllErrors(e);
		assertEquals(400, error.getStatusCodeValue());

		
	}
	
	@Test
	public void testhandleDateNotFoundException()
	{
		InvalidDateException e=new InvalidDateException();
		ResponseEntity<ErrorResponse> error=globalexception.handleDateNotFoundException(e);
		assertEquals(404, error.getStatusCodeValue());

		
	}
	
	@Test
	public void testhandleTokenValidationFailedException()
	{
		TokenValidationFailedException e=new TokenValidationFailedException();
		ResponseEntity<ErrorResponse> error=globalexception.handleTokenValidationFailedException(e);
		assertEquals(403, error.getStatusCodeValue());

		
	}

}
