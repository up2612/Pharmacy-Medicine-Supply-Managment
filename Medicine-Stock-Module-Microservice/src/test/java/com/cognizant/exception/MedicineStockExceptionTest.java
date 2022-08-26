package com.cognizant.exception;




import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

@SpringBootTest
public class MedicineStockExceptionTest {
	@InjectMocks
	GlobalErrorHandler globalerrorhandler;
	@Mock
	MedicinesByTargetAlimentException medicinesbytargetalimentexception;
	
	@Mock
	ErrorResponse errorresponse;
	
    	
	@Test
	public void testhandleTokenValidationException()
	{
		ResponseEntity<ErrorResponse> error=globalerrorhandler.handleTokenValidationException();
		assertEquals(500, error.getStatusCodeValue());

		
	}
	@Test
	public void testhandleAllException()
	{
            Exception e=new Exception("Invalid");
		ResponseEntity<ErrorResponse> error=globalerrorhandler.handleAllExceptions(e,null);
		assertEquals(500, error.getStatusCodeValue());

		
	}
	@Test
	public void testhandleMedicinesByTargetAlimentException()
	{
		ResponseEntity<ErrorResponse> error=globalerrorhandler.handleMedicinesByTargetAlimentException();
		assertEquals(500, error.getStatusCodeValue());

		
	}
	
	@Test
	public void testhandleStockAvailabilityException()
	{
		ResponseEntity<ErrorResponse> error=globalerrorhandler.handleStockAvailabilityException();
		assertEquals(500, error.getStatusCodeValue());

		
	}
	
	
}
