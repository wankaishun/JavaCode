package com.liqun.Exception;



public class DataillegalityException extends RuntimeException{
	/**
	 * 内容不合法异常 000001
	 */
	private static final long serialVersionUID = 1L;

	public DataillegalityException() {
		super();
	}
	
	public DataillegalityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public DataillegalityException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataillegalityException(String message) {
		super(message);
	}

	public DataillegalityException(Throwable cause) {
		super(cause);
	}
}
