package com.cg.exceptions;

public class InvalidAccountInformationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAccountInformationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountInformationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountInformationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountInformationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountInformationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
