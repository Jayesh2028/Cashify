package com.cashify.exception;

public class InvalidCredentialsException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException(String message) {
		super(message);
	}

	public InvalidCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}
}