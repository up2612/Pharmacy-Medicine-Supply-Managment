package com.cognizant.medicalrepresentativeschedulemicroserice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.medicalrepresentativeschedulemicroserice.model.RepSchedule;

@Repository
public interface ScheduleRepository extends JpaRepository<RepSchedule, Integer> {
	

}
