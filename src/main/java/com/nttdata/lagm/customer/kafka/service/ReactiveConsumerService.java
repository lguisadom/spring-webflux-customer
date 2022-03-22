package com.nttdata.lagm.customer.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;

import com.nttdata.lagm.customer.repository.CustomerRepository;

import reactor.core.publisher.Mono;

@Service
public class ReactiveConsumerService implements CommandLineRunner {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ReactiveConsumerService.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ReactiveProducerService reactiveProducerService;
	
	private final ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate;
	
	public ReactiveConsumerService(ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate) {
        this.reactiveKafkaConsumerTemplate = reactiveKafkaConsumerTemplate;
    }

    private Mono<Void> consumeIdProduceCustomer() {
    	LOGGER.info("consumeIdProduceCustomer.....");
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
                .doOnNext(id -> LOGGER.info("successfully consumed {}={}", "id", id))
                .doOnError(throwable -> LOGGER.error("something bad happened while consuming : {}", throwable.getMessage()))
                .next()
                .flatMap(id -> {
                	return customerRepository.findById(id)
                		.flatMap(customer -> {
                			reactiveProducerService.send(customer);
                			return Mono.empty();
                		});
                });
    }
    
    @Override
    public void run(String... args) {
    	LOGGER.info("ReactiveConsumerService.....");
    	consumeIdProduceCustomer().subscribe();
    }
}
