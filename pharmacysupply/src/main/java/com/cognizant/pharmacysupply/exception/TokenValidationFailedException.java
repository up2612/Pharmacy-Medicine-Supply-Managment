package com.cognizant.pharmacysupply.exception;


public class TokenValidationFailedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TokenValidationFailedException(String message) {
		super(message);
	}
}
