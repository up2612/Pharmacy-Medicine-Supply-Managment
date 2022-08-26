package com.cognizant.medicalrepresentativeschedulemicroserice.model;


import java.util.Date;


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

public class MedicineStock {

	

	private Integer id;
	private String name;
	private String chemicalComposition;
	private String targetAliment;
	private String pharmacyName;
	@Temporal(TemporalType.DATE)
	/*converting the date and time values from Java
	 *  object to compatible database type and retrieving back to the application.
	 */
	private Date dateOfExpiry;
	private Integer numberOfTabletsInStock;
}
