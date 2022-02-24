package com.nttdata.lagm.customer.springwebfluxcustomer.service;

import com.nttdata.lagm.customer.springwebfluxcustomer.model.CustomerType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerTypeService {
    void createCustomerType(CustomerType customerType);
    Mono<CustomerType> findByCustomerTypeId(Integer id);
    Flux<CustomerType> findAllCustomerType();
    Mono<CustomerType> updateCustomerType(CustomerType customerType);
    Mono<Void> deleteCustomerType(Integer id);
}
