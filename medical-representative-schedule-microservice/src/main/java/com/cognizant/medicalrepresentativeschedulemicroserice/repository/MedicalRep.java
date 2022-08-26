package com.cognizant.medicalrepresentativeschedulemicroserice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.medicalrepresentativeschedulemicroserice.model.MedicalRepresentative;

@Repository
public interface MedicalRep extends JpaRepository<MedicalRepresentative, Integer>{

}
