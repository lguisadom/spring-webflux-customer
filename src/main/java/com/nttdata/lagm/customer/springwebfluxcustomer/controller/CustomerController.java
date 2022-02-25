package com.nttdata.lagm.customer.springwebfluxcustomer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.lagm.customer.springwebfluxcustomer.model.Customer;
import com.nttdata.lagm.customer.springwebfluxcustomer.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private void create(@RequestBody Customer customer) {
		customerService.create(customer);
	}
	
	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ResponseStatus(HttpStatus.OK)
	private Flux<Customer> findAll() {
		return customerService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	private ResponseEntity<Mono<Customer>> findById(@PathVariable("id") Long id) {
		Mono<Customer> monoCustomer = customerService.findById(id);
		return new ResponseEntity<>(monoCustomer, monoCustomer != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	private Mono<Customer> update(@RequestBody Customer customer) {
		return customerService.update(customer);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	private Mono<Void> delete(@PathVariable("id") Long id) {
		return customerService.delete(id);
	}
}
