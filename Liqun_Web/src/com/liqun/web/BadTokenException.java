package com.liqun.web;

public class BadTokenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadTokenException() {
		super();
	}

	public BadTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BadTokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadTokenException(String message) {
		super(message);
	}

	public BadTokenException(Throwable cause) {
		super(cause);
	}

}
