package com.revature.exception;

public class UsersNotFoundException extends Exception{

	public UsersNotFoundException() {
		super();		
	}

	public UsersNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);		
	}

	public UsersNotFoundException(String message, Throwable cause) {
		super(message, cause);		
	}

	public UsersNotFoundException(String message) {
		super(message);		
	}

	public UsersNotFoundException(Throwable cause) {
		super(cause);		
	}

	
}
