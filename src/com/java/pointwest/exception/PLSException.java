package com.java.pointwest.exception;

public class PLSException extends Exception {
	private String customMessage;

	public PLSException(String message, Exception e) {
	super(e);
		customMessage = message;
	}

	public String getCustomMessage() {
		return customMessage;
	}

}
