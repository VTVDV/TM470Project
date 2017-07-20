package com.veronica.tm470.exceptions;

public class WebDBException extends Exception {
	
	
	private int errorCode;

	public WebDBException(int errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
