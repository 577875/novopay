package com.example.ewallet.exceptions;



/** Exception when balance insufficient */
public class BalanceLowException extends Exception {

	private String message;

	public BalanceLowException() {
		super();
	}

	/**
	 * @param message
	 */
	public BalanceLowException(String message) {
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
