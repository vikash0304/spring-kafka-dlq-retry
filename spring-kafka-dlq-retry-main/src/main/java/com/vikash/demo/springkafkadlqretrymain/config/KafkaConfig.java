package com.vikash.demo.springkafkadlqretrymain.config;

import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

    @Value(value = "${spring.kafka.dead_letter_topic:}")
    private String deadLetterTopic;
    private KafkaProps kafkaProps;

    private KafkaTemplate kafkaTemplate;

    KafkaConfig(KafkaTemplate kafkaTemplate, KafkaProps kafkaProps) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaProps = kafkaProps;
    }

    @Bean("kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Object, Object> concurrentKafkaListenerContainerFactory
                = new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(new DefaultKafkaConsumerFactory(kafkaProps.consumerProps()));
        DeadLetterPublishingRecoverer deadLetterPublishingRecoverer =
            new DeadLetterPublishingRecoverer(kafkaTemplate, (record, ex) -> {
                System.out.println("Exception occurred sending the record to the error topic: "+ ex.getMessage()+" And  "+ deadLetterTopic);
                return new TopicPartition(deadLetterTopic, -1);
            });
        CommonErrorHandler errorHandler = new DefaultErrorHandler(deadLetterPublishingRecoverer, new FixedBackOff(0L, 1L));
        concurrentKafkaListenerContainerFactory.setCommonErrorHandler(errorHandler);
        return concurrentKafkaListenerContainerFactory;
    }

}
