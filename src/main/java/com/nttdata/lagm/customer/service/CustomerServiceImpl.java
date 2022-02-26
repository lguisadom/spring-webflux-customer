package com.nttdata.lagm.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.lagm.customer.model.Customer;
import com.nttdata.lagm.customer.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void create(Customer customer) {
		customerRepository.save(customer).subscribe();
	}

	@Override
	public Flux<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Mono<Customer> findById(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public Mono<Customer> update(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Mono<Void> delete(Long id) {
		return customerRepository.deleteById(id);
	}

}
