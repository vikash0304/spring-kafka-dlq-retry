package com.vikash.demo.retryconsumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikash.demo.retryconsumer.model.MyDTO;

@Service
public class RetryMessageListener {

	private ObjectMapper objectMapper = new ObjectMapper();

	@KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
	public void listen(ConsumerRecord<String, String> consumerRecord) {
		System.out.println("Started consuming message on topic: " + consumerRecord.topic() + ", offset: "
				+ consumerRecord.offset() + ", message: " + consumerRecord.value());

		if (consumerRecord.offset() % 2 != 0)
			throw new IllegalStateException("This is something odd.");

		try {
			MyDTO myDto = objectMapper.readValue(consumerRecord.value(), MyDTO.class);
			System.out.println("Finished consuming message on topic: " + consumerRecord.topic() + ", offset: "
					+ consumerRecord.offset() + ", message: " + myDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
