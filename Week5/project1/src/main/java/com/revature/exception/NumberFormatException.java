package com.revature.exception;

public class NumberFormatException extends Exception{

	public NumberFormatException() {
		super();		
	}

	public NumberFormatException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);		
	}

	public NumberFormatException(String message, Throwable cause) {
		super(message, cause);		
	}

	public NumberFormatException(String message) {
		super(message);
		
	}

	public NumberFormatException(Throwable cause) {
		super(cause);
		
	}

	
}
