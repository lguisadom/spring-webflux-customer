package com.nttdata.lagm.customer.config.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;

import com.nttdata.lagm.customer.model.Customer;
import com.nttdata.lagm.customer.repository.CustomerRepository;

import reactor.core.publisher.Mono;

@Service
public class ReactiveConsumerService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ReactiveConsumerService.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ReactiveProducerService reactiveProducerService;
	
	private final ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate;
	
	public ReactiveConsumerService(ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate) {
        this.reactiveKafkaConsumerTemplate = reactiveKafkaConsumerTemplate;
    }

    private Mono<Void> consumeDniProduceCustomer() {
        return reactiveKafkaConsumerTemplate
                .receiveAutoAck()
                // .delayElements(Duration.ofSeconds(2L)) // BACKPRESSURE
                .doOnNext(consumerRecord -> LOGGER.info("received key={}, value={} from topic={}, offset={}",
                        consumerRecord.key(),
                        consumerRecord.value(),
                        consumerRecord.topic(),
                        consumerRecord.offset())
                )
                .map(ConsumerRecord::value)
                .doOnNext(dni -> LOGGER.info("successfully consumed {}={}", "dni", dni))
                .doOnError(throwable -> LOGGER.error("something bad happened while consuming : {}", throwable.getMessage()))
                .next()
                .flatMap(dni -> {
                	return customerRepository.findBydni(dni)
                		.flatMap(customer -> {
                			reactiveProducerService.send(customer);
                			return Mono.empty();
                		});
                });
    }
}
