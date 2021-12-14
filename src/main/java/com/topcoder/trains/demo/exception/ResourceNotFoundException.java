package com.topcoder.trains.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String msag) {
		super(msag);
	}
}