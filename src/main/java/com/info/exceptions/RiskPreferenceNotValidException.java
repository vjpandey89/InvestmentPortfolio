package com.info.exceptions;



/**
 * @author vijay
 *Custom Exception class for RiskPreference Validation
 *
 */
public class RiskPreferenceNotValidException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public RiskPreferenceNotValidException(String message) {
		super(message);
	}

}
