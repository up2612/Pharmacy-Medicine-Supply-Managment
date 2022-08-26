package com.cognizant.medicalrepresentativeschedulemicroserice.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicalrepresentativeschedulemicroserice.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentativeschedulemicroserice.feignclient.MedicineStockFeignClient;
import com.cognizant.medicalrepresentativeschedulemicroserice.model.Doctor;
import com.cognizant.medicalrepresentativeschedulemicroserice.model.MedicalRepresentative;
import com.cognizant.medicalrepresentativeschedulemicroserice.model.MedicineStock;
import com.cognizant.medicalrepresentativeschedulemicroserice.model.RepSchedule;
import com.cognizant.medicalrepresentativeschedulemicroserice.repository.DoctorRepository;
import com.cognizant.medicalrepresentativeschedulemicroserice.repository.MedRepRepository;
import com.cognizant.medicalrepresentativeschedulemicroserice.repository.ScheduleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedRepScheduleServiceImpl implements MedRepScheduleService {
	@Autowired
	private MedRepRepository medicalRepresentativeRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private DoctorRepository doctorRepositry;

	@Autowired
	AuthenticationFeignClient authFeignClient;
	
	@Autowired
	MedicineStockFeignClient medicinestockfeign;

	@Override
	public List<RepSchedule> getRepSchedule(String token, LocalDate scheduleStartDate) {

		List<RepSchedule> repSchedules = new ArrayList<>();

		List<Doctor> doctors = doctorRepositry.findAll();

		log.debug("doctors : {}", doctors);

		List<MedicalRepresentative> medicalRepresentatives = medicalRepresentativeRepository.findAll();

		log.debug("medicalRepresentatives : {}", medicalRepresentatives);

		LocalDate localDate = scheduleStartDate;

		LocalTime now = LocalTime.now();
		LocalTime one = LocalTime.of(13, 0);

		LocalDate today = LocalDate.now();
		if (scheduleStartDate.isBefore(today)) {
			log.info("have given invalid date");
			return repSchedules;
		}

		if (scheduleStartDate.equals(today)) {

			if (now.isAfter(one)) {
				log.info("entered date after 1PM, schedule start from next date");
				localDate = localDate.plusDays(1);
			}

		}

		for (int i = 0; i < doctors.size(); i++) {

			if (localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
				log.info("skipped sundays");
				localDate = localDate.plusDays(1);
			}

			Doctor doctor = doctors.get(i);
			MedicalRepresentative medicalRepresentative = medicalRepresentatives.get(i % 3);

			RepSchedule repSchedule = new RepSchedule();
			
			repSchedule.setRepName(medicalRepresentative.getName());
			repSchedule.setDoctorName(doctor.getName());
			repSchedule.setDoctorContactNumber(doctor.getPhoneNumber());
			repSchedule.setMeetingDate(localDate);
			repSchedule.setMeetingSlot("1 PM to 2 PM");
			repSchedule.setTreatingAilment(doctor.getTreatingAilment());
			
			List<MedicineStock> medicinesByTreatingAilment = medicinestockfeign
					.getMedicinesByTreatingAilment(doctor.getTreatingAilment(), token);
			
			String[] medicineresult = new String[medicinesByTreatingAilment.size()];
			int j=0;
			for(MedicineStock s:medicinesByTreatingAilment)
			{
		
				medicineresult[j]=s.getName();
				j++;
			}

			repSchedule.setMedicines(medicineresult);

			log.debug("repSchedule : {}", repSchedule);

			repSchedules.add(repSchedule);

			localDate = localDate.plusDays(1);
		}
		repSchedules = scheduleRepository.saveAll(repSchedules);
		log.debug("repSchedules : {}", repSchedules);

		return repSchedules;
	}

}