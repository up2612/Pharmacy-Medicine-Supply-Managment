package com.cognizant.exception;


import java.util.ArrayList;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(TokenValidationException.class)
    public final ResponseEntity<ErrorResponse>handleTokenValidationException() {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST,"Invalid Token!!!!");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST,"Sorry..!We could not process your request!!!!");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
	@ExceptionHandler(StockAvailabilityException.class)
    public final ResponseEntity<ErrorResponse> handleStockAvailabilityException() {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST,"No Stock!!!!");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
	@ExceptionHandler(MedicinesByTargetAlimentException.class)
    public final ResponseEntity<ErrorResponse> handleMedicinesByTargetAlimentException() {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST,"No Medicines are available under this TargetAliment!!!!");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
	
	
	
	

}
