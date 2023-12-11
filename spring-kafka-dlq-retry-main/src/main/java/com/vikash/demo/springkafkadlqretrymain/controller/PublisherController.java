package com.vikash.demo.springkafkadlqretrymain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikash.demo.springkafkadlqretrymain.model.MyDTO;
import com.vikash.demo.springkafkadlqretrymain.publisher.KafkaPublisher;

@RestController
@RequestMapping("/kafka")
public class PublisherController {

	@Autowired
	KafkaPublisher publisher;

	@PostMapping(value = "/publish")
	public void publish(@RequestBody MyDTO dto) {
		System.out.println("Publishing the event: " + dto);
		publisher.publish(dto);
	}
}
