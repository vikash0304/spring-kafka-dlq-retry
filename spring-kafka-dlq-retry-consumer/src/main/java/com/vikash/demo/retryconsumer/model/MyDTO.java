package com.vikash.demo.retryconsumer.model;

public class MyDTO {
    private String eventType;

    private String message;

	public MyDTO() {
		super();
	}

	public MyDTO(String eventType, String message) {
		super();
		this.eventType = eventType;
		this.message = message;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
