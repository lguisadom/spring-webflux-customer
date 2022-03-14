package com.nttdata.lagm.customer.service;

import com.nttdata.lagm.customer.model.CustomerProfile;
import com.nttdata.lagm.customer.repository.CustomerProfileRepository;
import com.nttdata.lagm.customer.service.CustomerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerProfileServiceImpl implements CustomerProfileService {

    @Autowired
    private CustomerProfileRepository customerProfileRepository;

    @Override
    public Mono<CustomerProfile> save(CustomerProfile customerProfile) {
        return customerProfileRepository.save(customerProfile);
    }

    @Override
    public Flux<CustomerProfile> findAll() {
        return customerProfileRepository.findAll();
    }
}
