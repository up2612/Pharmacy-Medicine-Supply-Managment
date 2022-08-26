package com.cognizant.portal.model;

import java.util.Date;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "medicine_supply")
public class MedicineSupply {


	@JsonIgnore
	private Integer id;

	private String pharmacyname;

	private String medicinename;

	private Integer supplycount;
	private String tranDate;
	//private Date tranDate;

}