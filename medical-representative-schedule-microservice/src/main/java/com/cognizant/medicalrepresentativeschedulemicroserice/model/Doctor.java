package com.cognizant.medicalrepresentativeschedulemicroserice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String phoneNumber;
	private String treatingAilment;
	

}
