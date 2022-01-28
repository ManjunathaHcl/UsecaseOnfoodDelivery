package com.homelyfooduser.dto;

import ch.qos.logback.core.status.Status;

public class ResponseDto {
	
	
	private String status;
	
	
	private String message;


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
