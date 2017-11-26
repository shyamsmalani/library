package com.library.exception;

public class LibraryException extends Exception {

	private String message = null;

	public LibraryException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
