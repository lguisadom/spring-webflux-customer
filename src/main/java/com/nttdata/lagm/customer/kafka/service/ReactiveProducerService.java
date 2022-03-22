package com.nttdata.lagm.customer.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;

import com.nttdata.lagm.customer.model.Customer;

@Service
public class ReactiveProducerService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ReactiveProducerService.class);
    private final ReactiveKafkaProducerTemplate<String, Customer> reactiveKafkaProducerTemplate;

    @Value(value = "${kafka.topic.producer.findById}")
    private String topic;

    public ReactiveProducerService(ReactiveKafkaProducerTemplate<String, Customer> reactiveKafkaProducerTemplate) {
        this.reactiveKafkaProducerTemplate = reactiveKafkaProducerTemplate;
    }

    public void send(Customer customer) {
        LOGGER.info("send to topic={}, {}={},", topic, Customer.class.getSimpleName(), customer);
        reactiveKafkaProducerTemplate.send(topic, customer)
                .doOnSuccess(senderResult -> LOGGER.info("sent {} offset : {}", customer, senderResult.recordMetadata().offset()))
                .subscribe();
    }
}
