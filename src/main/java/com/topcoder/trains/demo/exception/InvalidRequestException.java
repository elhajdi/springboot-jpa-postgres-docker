package com.topcoder.trains.demo.exception;

public class InvalidRequestException extends RuntimeException {

	private static final long serialVersionUId = 2342L;
	/**
	 * Constructor
	 */
	public InvalidRequestException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
	public InvalidRequestException(final String message) {
		super(message);
	}
	public InvalidRequestException() {
		super();
	}
	public InvalidRequestException( final Throwable throwable) {
		super(throwable);
	}
	
}

