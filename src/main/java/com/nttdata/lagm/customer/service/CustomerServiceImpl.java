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
	
	private Mono<Void> assertCustomerNotExistByDni(String dni) {
		return this.customerRepository.findBydni(dni)
				.flatMap(customer -> { 
					return Mono.error(new Exception("Customer with dni " + dni + " is already registered"));
				})
				.then();
	}
	
	@Override
	public Mono<Customer> create(Customer customer) {
		return assertCustomerNotExistByDni(customer.getDni())
				.then(customerRepository.save(customer));
	}

	@Override
	public Flux<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Mono<Customer> findById(Long id) {
		return customerRepository.findById(id);
	}
	
//	@Override
//	public Mono<CustomerResponse> findByDni(String dni) {	
//		return customerRepository.findBydni(dni).map(customer -> {
//			CustomerResponse customerResponse = new CustomerResponse();
//			customerResponse.setDni(customer.getDni());
//			customerResponse.setEmail(customer.getEmail());
//			customerResponse.setFirstName(customer.getFirstName());
//			customerResponse.setLastName(customer.getLastName());
//			customerResponse.setPhone(customer.getPhone());
//			customerResponse.setCustomerTypeId(customer.getCustomerTypeId());
//			return customerResponse;
//		});
//	}
	
	public Mono<Customer> findByDni(String dni) {
		return customerRepository.findBydni(dni);
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
