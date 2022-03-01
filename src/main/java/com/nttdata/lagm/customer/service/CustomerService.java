package com.nttdata.lagm.customer.service;

import com.nttdata.lagm.customer.dto.CustomerResponse;
import com.nttdata.lagm.customer.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
	public void create(Customer customer);
	public Flux<Customer> findAll();
	public Mono<Customer> findById(Long id);
	public Mono<CustomerResponse> findByDni(String dni);
	public Mono<Customer> update(Customer customer);
	public Mono<Void> delete(Long id);
}
