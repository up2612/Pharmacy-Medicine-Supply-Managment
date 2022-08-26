package com.cognizant.medicalrepresentativeschedulemicroserice.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.medicalrepresentativeschedulemicroserice.exception.InvalidDateException;
import com.cognizant.medicalrepresentativeschedulemicroserice.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedulemicroserice.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentativeschedulemicroserice.model.Doctor;
import com.cognizant.medicalrepresentativeschedulemicroserice.model.MedicalRepresentative;
import com.cognizant.medicalrepresentativeschedulemicroserice.model.RepSchedule;
import com.cognizant.medicalrepresentativeschedulemicroserice.repository.DoctorRepository;
import com.cognizant.medicalrepresentativeschedulemicroserice.service.MedRepScheduleServiceImpl;
import com.cognizant.medicalrepresentativeschedulemicroserice.service.MedRepServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class MedRepScheduleControllerTest {
	@Mock
	private MedRepScheduleServiceImpl medicalRepresentativeScheduleService;

	@Mock
	private AuthenticationFeignClient authenticationFeignClient;

	@InjectMocks
	private MedRepScheduleController medicalRepresenativeScheduleController;

	@Mock
	private RepSchedule repSchedule;
	
	@Mock
	MedicalRepresentative medicalrepresentative;
	
	@Mock
	Doctor doctor;

	@Mock
	private List<RepSchedule> medicineStockList;

	@MockBean
	private MedRepScheduleServiceImpl scheduleService;
	
	@Mock
	private MedRepServiceImpl repService;
	
	@Mock
	private DoctorRepository docrep;
	


	


	@Test
	public void getMedicalRepresentativesTest(){
		when(authenticationFeignClient.authorizeTheRequest("token")).thenReturn(true);
		List<MedicalRepresentative> medicalrepresentativelist = new ArrayList<>();
		MedicalRepresentative medicalreps = new MedicalRepresentative(1,"Satwik", "9456481521");
		medicalrepresentativelist.add(medicalreps);
		when(repService.getMedicalRepresentatives()).thenReturn(medicalrepresentativelist);
		List<MedicalRepresentative> list = new ArrayList<>();
		try {
			list = medicalRepresenativeScheduleController.getMedicalRepresentatives("token");
		} catch (TokenValidationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(list,medicalrepresentativelist);
	
	}

	

	@Test
	public void getDoctorsTest(){
		when(authenticationFeignClient.authorizeTheRequest("token")).thenReturn(true);
		List<Doctor> doctorslist = new ArrayList<>();
		Doctor docs = new Doctor(1,"Sagar","9449569825","Orthopaedics");
		doctorslist.add(docs);
		when(docrep.findAll()).thenReturn(doctorslist);
		List<Doctor> list = new ArrayList<>();
		try {
			list = medicalRepresenativeScheduleController.getDoctors("token");
		} catch (TokenValidationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(list,doctorslist);
	}
	
	@Test
	public void getMedicalRepresentativesFalseTokenTest() {
		when(authenticationFeignClient.authorizeTheRequest("token")).thenReturn(false);
		List<MedicalRepresentative> medicalrepresentativelist = new ArrayList<>() ;
		try {
			medicalrepresentativelist = medicalRepresenativeScheduleController.getMedicalRepresentatives("token");
		} catch (TokenValidationFailedException e) {
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void getDoctorsFalseTokenTest() {
		when(authenticationFeignClient.authorizeTheRequest("token")).thenReturn(false);
		List<Doctor> docslist = new ArrayList<>() ;
		try {
			docslist = medicalRepresenativeScheduleController.getDoctors("token");
		} catch (TokenValidationFailedException e) {
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void retrieveDetailsForCourse() throws InvalidDateException, TokenValidationFailedException {
		
		when(authenticationFeignClient.authorizeTheRequest("token")).thenReturn(true);
		ResponseEntity<?> allMedicineStockInformation = medicalRepresenativeScheduleController.getRepSchedule("token", "2021-06-25");
		assertNotNull(allMedicineStockInformation);
		
	}
 
	
}
