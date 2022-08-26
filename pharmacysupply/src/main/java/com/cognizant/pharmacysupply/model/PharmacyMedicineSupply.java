package com.cognizant.pharmacysupply.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="medicine_supply")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
public class PharmacyMedicineSupply{
	@GeneratedValue(strategy = GenerationType.AUTO )
	@Id
	//@JsonIgnore
	private Integer Id;
	private String pharmacyname;
	private String medicinename;
	private Integer supplycount;
	//private Date tranDate;

	private String tranDate;
	//private Integer currentstock;//we have to remove it in future
	//private String messaString;//we have to remove it while intergration.
	
}
