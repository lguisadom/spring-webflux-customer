package com.nttdata.lagm.customer.service;

import com.nttdata.lagm.customer.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
	public Mono<Customer> create(Customer customer);
	public Flux<Customer> findAll();
	public Mono<Customer> findById(String id);
	// public Mono<CustomerResponse> findByDni(String dni);
	public Mono<Customer> findByDni(String dni);
	public Mono<Customer> update(Customer customer);
	public Mono<Void> delete(String id);
}
