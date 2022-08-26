package com.cognizant.medicalrepresentativeschedulemicroserice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.medicalrepresentativeschedulemicroserice.model.Doctor;

@Repository
public interface DoctorRepository  extends JpaRepository<Doctor, Integer>{

}
