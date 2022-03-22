package com.nttdata.lagm.customer.kafka.config;

import java.util.Map;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;

import com.nttdata.lagm.customer.model.Customer;

import reactor.kafka.sender.SenderOptions;

@Configuration
public class ReactiveKafkaProducerConfig {
    @Bean
    public ReactiveKafkaProducerTemplate<String, Customer> reactiveKafkaProducerTemplate(
            KafkaProperties properties) {
        Map<String, Object> props = properties.buildProducerProperties();
        return new ReactiveKafkaProducerTemplate<String, Customer>(SenderOptions.create(props));
    }
}