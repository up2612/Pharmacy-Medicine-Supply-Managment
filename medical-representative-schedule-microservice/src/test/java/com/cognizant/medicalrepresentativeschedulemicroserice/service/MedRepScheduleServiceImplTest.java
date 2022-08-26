package com.cognizant.medicalrepresentativeschedulemicroserice.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cognizant.medicalrepresentativeschedulemicroserice.model.Doctor;
import com.cognizant.medicalrepresentativeschedulemicroserice.model.RepSchedule;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MedRepScheduleServiceImplTest {
	@Autowired
	private MedRepScheduleServiceImpl medicalRepresentativeScheduleService;
//
	RepSchedule repSchedule = new RepSchedule();
//	@Mock
//	private RepSchedule repSchedule;

	List<Date> scheduleDates;

	List<Doctor> doctorDetailsList;
	

	@Before
	public void setup() {
		String[] medicines = { "Crocin", "Percocet" };
		
		repSchedule.setId(1);
		repSchedule.setRepName("Satwik");
		repSchedule.setDoctorName("Sagar");
		repSchedule.setTreatingAilment("Orthopaedics");
		repSchedule.setMedicines(medicines);
		repSchedule.setMeetingSlot("1 AM to 2 PM");
		repSchedule.setMeetingDate(LocalDate.of(2021, 06, 22));
		repSchedule.setDoctorContactNumber("9449569825");
	}

	@Test
	public void testGetRepSchedule() throws FeignException {
		List<RepSchedule> repschedulelist = medicalRepresentativeScheduleService.getRepSchedule("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJVZGRlc2hheSBQYW5kZXkiLCJleHAiOjE2MjQ0NTI4NzYsImlhdCI6MTYyNDQ1MTA3Nn0.ILtmAYw16egRGCwYndHOqFB13S39-_obiefoNykSwvczZEOdcQQ6LBemAh6jZeOuAAcFoSWVrSy0lMKjiO0Iyg", LocalDate.of(2021, 06, 30));
		//boolean result = repschedulelist.isEmpty();
		//assertFalse(result);
		assertNotNull(repschedulelist);
	}

}
