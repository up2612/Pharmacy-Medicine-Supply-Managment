package com.cognizant.medicalrepresentativeschedulemicroserice.exception;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cognizant.medicalrepresentativeschedulemicroserice.model.ErrorResponse;


@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
	
	@Autowired
	ErrorResponse errorResponse;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllErrors(Exception ex) {
		
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setReason("BAD_REQUEST");
		errorResponse.setStatus(HttpStatus.BAD_REQUEST);


		return ResponseEntity.badRequest().body(errorResponse);
	}


	@ExceptionHandler(InvalidDateException.class)
	public ResponseEntity<ErrorResponse> handleDateNotFoundException(InvalidDateException e) {
		
		errorResponse.setMessage("Please enter the date in yyyy-mm-dd format");
		errorResponse.setReason("You need to provide date in yyyy-mm-dd format");
		errorResponse.setStatus(HttpStatus.NOT_FOUND);
		

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(TokenValidationFailedException.class)
	public ResponseEntity<ErrorResponse> handleTokenValidationFailedException(TokenValidationFailedException e) {
		
		errorResponse.setMessage("Your token is invalid");
		errorResponse.setReason("Your token might have been expired or you have entered wrong token");
		errorResponse.setStatus(HttpStatus.FORBIDDEN);

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.FORBIDDEN);
	}
	
	
}
