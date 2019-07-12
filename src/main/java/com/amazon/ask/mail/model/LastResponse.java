package com.amazon.ask.mail.model;

import java.io.Serializable;

public class LastResponse implements Serializable{
	
	private boolean sessionCheckRequired;
	private String responseText;
	
	public LastResponse(boolean sessionCheckRequired, String responseText) {
		this.sessionCheckRequired = sessionCheckRequired;
		this.responseText = responseText;
	}
	
	public boolean isSessionCheckRequired() {
		return sessionCheckRequired;
	}
	public void setSessionCheckRequired(boolean sessionCheckRequired) {
		this.sessionCheckRequired = sessionCheckRequired;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	
}
