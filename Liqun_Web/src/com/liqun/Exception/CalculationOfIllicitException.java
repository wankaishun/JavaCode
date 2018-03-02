package com.liqun.Exception;

public class CalculationOfIllicitException extends RuntimeException {
	/**
	 * 计算非法异常 000003
	 */
	private static final long serialVersionUID = 1L;

	public CalculationOfIllicitException() {
		super();
	}
	
	public CalculationOfIllicitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public CalculationOfIllicitException(String message, Throwable cause) {
		super(message, cause);
	}

	public CalculationOfIllicitException(String message) {
		super(message);
	}

	public CalculationOfIllicitException(Throwable cause) {
		super(cause);
	}
}
