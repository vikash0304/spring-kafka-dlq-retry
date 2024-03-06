package com.vikash.demo.springkafkadlqretrymain.listener;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.vikash.demo.springkafkadlqretrymain.model.MyDTO;


@Service
public class KafkaMainListener {

    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(ConsumerRecord<String, MyDTO> consumerRecord) {
        System.out.println("Started consuming message on topic: "+consumerRecord.topic()+"  offset: "+consumerRecord.offset()+" message: "+ consumerRecord.value());

        if(consumerRecord.offset() % 2 != 0)
            throw new RuntimeException("This is really odd.");

        System.out.println("Finished consuming message on topic: "+consumerRecord.topic()+", offset: "+consumerRecord.offset()+", message: "+consumerRecord.value());
    }
}
