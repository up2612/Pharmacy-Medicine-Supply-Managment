package com.cognizant.pharmacysupply.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "medicine_supply")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDemand {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@JsonIgnore
	private Integer Id;
	@NotEmpty(message = "medicine name should not be empty")
	@NotNull(message = "medicine name should not be null")
	private String medicinename;
	private Integer demandcount;
	//private Date tranDate;
	private String tranDate;
	
}
