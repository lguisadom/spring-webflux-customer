package com.nttdata.lagm.customer.service;

import com.nttdata.lagm.customer.model.CustomerProfile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerProfileService {
	Mono<CustomerProfile> save(CustomerProfile customerProfile);
    Flux<CustomerProfile> findAll();
}
