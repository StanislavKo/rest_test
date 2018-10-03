package com.hsd.upwork.calculator.exception;

public class IncorrectArgumentException extends Exception {

	public IncorrectArgumentException() {
		super();
	}

	public IncorrectArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IncorrectArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectArgumentException(String message) {
		super(message);
	}

	public IncorrectArgumentException(Throwable cause) {
		super(cause);
	}

}
