package com.cognizant.medicalrepresentativeschedulemicroserice.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


import com.cognizant.medicalrepresentativeschedulemicroserice.controller.MedRepScheduleController;
import com.cognizant.medicalrepresentativeschedulemicroserice.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentativeschedulemicroserice.model.MedicalRepresentative;
import com.cognizant.medicalrepresentativeschedulemicroserice.model.RepSchedule;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedRepServiceImplTest {

	@Autowired
	MedRepServiceImpl medRepserviceimpl;
	
	@Test
	public void getMedicalRepresentativesTest() {
		List<MedicalRepresentative> medicalrepresentativelist = medRepserviceimpl.getMedicalRepresentatives();
		assertNotNull(medicalrepresentativelist);
		
	}



}
