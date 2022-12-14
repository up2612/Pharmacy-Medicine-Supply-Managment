package com.cognizant.portal.model;

import java.util.Date;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@Table(name = "medicine_demand")
public class MedicineDemand {

	private Integer id;
	

	@NotEmpty(message = "Medicine field must not be empty")
	@NotNull(message = "Medicine field must not be null")
	private String medicinename;
	

	private Integer demandcount;
	private String tranDate;
	//private Date tranDate;

}
