package com.revature.exception;

public class ReceiptNotFoundException extends Exception{

	public ReceiptNotFoundException() {
		super();		
	}

	public ReceiptNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);		
	}

	public ReceiptNotFoundException(String message, Throwable cause) {
		super(message, cause);		
	}

	public ReceiptNotFoundException(String message) {
		super(message);		
	}

	public ReceiptNotFoundException(Throwable cause) {
		super(cause);		
	}

	
}
