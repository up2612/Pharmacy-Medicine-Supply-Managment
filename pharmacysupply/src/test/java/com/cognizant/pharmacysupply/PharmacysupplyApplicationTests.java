package com.cognizant.pharmacysupply;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PharmacysupplyApplicationTests {

	@Mock
	PharmacysupplyApplication PharmacySupplyApplication;
	
	@Test
	void contextLoads() {
		assertNotNull(PharmacySupplyApplication);
	}
	 @SuppressWarnings("static-access")
	@Test
	   public void main() {
	      PharmacySupplyApplication.main(new String[] {});
	   }
}
