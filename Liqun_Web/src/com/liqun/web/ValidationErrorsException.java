package com.liqun.web;

import org.springframework.validation.Errors;

public class ValidationErrorsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	Errors errors;
	
	public ValidationErrorsException(Errors errors) {
//		super(errors.get)
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}
	


}
