package com.liqun.Exception;

public class NotEmptyException extends RuntimeException {
	/**
	 * 必填选项异常 000002
	 */
	private static final long serialVersionUID = 1L;

	public NotEmptyException() {
		super();
	}
	
	public NotEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public NotEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotEmptyException(String message) {
		super(message);
	}

	public NotEmptyException(Throwable cause) {
		super(cause);
	}
}
