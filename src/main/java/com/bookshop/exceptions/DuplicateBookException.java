package com.bookshop.exceptions;

public class DuplicateBookException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateBookException() {
	}

	public DuplicateBookException(String message) {
		super(message);
	}

	public DuplicateBookException(Throwable cause) {
		super(cause);
	}

	public DuplicateBookException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateBookException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
