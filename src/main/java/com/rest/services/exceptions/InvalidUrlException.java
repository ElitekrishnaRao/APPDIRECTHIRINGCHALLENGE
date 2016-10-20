package com.rest.services.exceptions;

public class InvalidUrlException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidUrlException() {
	}

	public InvalidUrlException(String message) {
		super(message);
	}

	public InvalidUrlException(Throwable cause) {
		super(cause);
	}

	public InvalidUrlException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidUrlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
