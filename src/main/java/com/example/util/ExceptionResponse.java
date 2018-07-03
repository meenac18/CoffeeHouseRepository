package com.example.util;

import java.util.Date;
import java.util.List;

public class ExceptionResponse {
	 private Date timestamp;
	  private String errorMessage;
	  private String errorCode;
	 

	  public ExceptionResponse(Date timestamp, String errorMessage, String errorCode) {
	    this.timestamp = timestamp;
	    this.errorMessage = errorMessage;
	    this.errorCode = errorCode;
	  }

	public ExceptionResponse() {
		// TODO Auto-generated constructor stub
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return errorMessage;
	}

	public void setMessage(String message) {
		this.errorMessage = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	
	
	  
}
