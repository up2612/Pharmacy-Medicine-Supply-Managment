package com.cognizant.medicalrepresentativeschedulemicroserice.model;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	
	private HttpStatus status;
	private String message;
	private String reason;

}