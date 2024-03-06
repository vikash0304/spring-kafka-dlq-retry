package com.vikash.demo.retryconsumer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FailedMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String message;

	@Column
	private String topic;

	@Column
	private Long consumerOffset;

	@Column
	private String exception;

	public FailedMessage() {
	}

	public FailedMessage(String message) {
		this.message = message;
	}

	public FailedMessage(String message, String topic, Long consumerOffset, String exception) {
		this.message = message;
		this.exception = exception;
		this.consumerOffset = consumerOffset;
		this.topic = topic;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Long getConsumerOffset() {
		return consumerOffset;
	}

	public void setConsumerOffset(Long consumerOffset) {
		this.consumerOffset = consumerOffset;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	@Override
	public String toString() {
		return "FailedMessage [id=" + id + ", message=" + message + ", topic=" + topic + ", consumerOffset="
				+ consumerOffset + ", exception=" + exception + "]";
	}
}
