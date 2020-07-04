package com.example.ewallet.exceptions;



/** Given User not available */
public class UserNotFoundException extends Exception {

	private String message;

	public UserNotFoundException() {
		super();
	}

	/**
	 * @param message
	 */
	public UserNotFoundException(String message) {
		super();
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
