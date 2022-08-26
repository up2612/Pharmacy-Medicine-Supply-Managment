package com.cognizant.model;

import java.util.Date;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="medicine_stock")
public class MedicineStock {

	
	@Id
	private Integer id;
	private String name;
	private String chemicalComposition;
	private String targetAliment;
	private String pharmacyName;
	@Temporal(TemporalType.DATE)
	private Date dateOfExpiry;
	private Integer numberOfTabletsInStock;
}
