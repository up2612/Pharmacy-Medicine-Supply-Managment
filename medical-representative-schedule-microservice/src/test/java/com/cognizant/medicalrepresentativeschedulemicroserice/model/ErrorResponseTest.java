package com.cognizant.medicalrepresentativeschedulemicroserice.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import static org.junit.Assert.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ErrorResponseTest {
	

	ErrorResponse errorResponse= new ErrorResponse();
	@Before
	public void setup() {

		
		errorResponse.setStatus(HttpStatus.OK);
		errorResponse.setReason("Bad request");
		errorResponse.setMessage("Please provide valid value");

	}

	@Test
	public void testMedicineStockDetails() throws Exception {

	assertEquals(HttpStatus.OK, errorResponse.getStatus());
	assertEquals("Bad request", errorResponse.getReason());
	assertEquals("Please provide valid value", errorResponse.getMessage());

	}

	@Test
	public void testAllArgsConstructor() {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.OK, "Bad request",
				"Please provide valid value");
		
		assertNotNull(errorResponse);
	}

}
