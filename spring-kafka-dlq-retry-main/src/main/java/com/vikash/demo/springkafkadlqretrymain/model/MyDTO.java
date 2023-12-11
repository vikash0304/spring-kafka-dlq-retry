package com.vikash.demo.springkafkadlqretrymain.model;

public class MyDTO {
    private String eventType;

    private String message;

	public MyDTO() {
		super();
		// TODO Auto-generated constructor stub
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
