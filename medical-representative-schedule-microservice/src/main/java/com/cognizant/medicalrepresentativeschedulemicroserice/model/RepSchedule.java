package com.cognizant.medicalrepresentativeschedulemicroserice.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RepSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String repName;
	private String doctorName;
	private String meetingSlot;
	private LocalDate meetingDate;
	private String doctorContactNumber;
	private String[] medicines;
	private String treatingAilment;
	/*@ManyToOne
	private Doctor doctor;
	@ManyToOne
	private MedicalRepresentative medicalRep;*/
}