package com.cognizant.pharmacysupply.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class MedicineNotFoundExceptionTest {

	@Mock
	private MedicineNotFoundException medicineNotFoundException;

	@Test
	public void testOneArgConstructor() {
		MedicineNotFoundException medicineNotFoundException = new MedicineNotFoundException("Medicine not found.");
		assertEquals("Medicine not found.", medicineNotFoundException.getMessage());
	}
	
//	@Test
//	public void testNoArgsConstructor() {
//		MedicineNotFoundException exception = new MedicineNotFoundException(");
//		assertEquals(null, exception.getMessage());
//	}
}
