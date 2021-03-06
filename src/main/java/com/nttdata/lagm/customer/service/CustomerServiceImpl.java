package com.nttdata.lagm.customer.service;

import com.nttdata.lagm.customer.util.Constants;
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

	private Mono<Void> checkCustomerProfile(Customer customer) {
		System.out.println("customer: " + customer);
		if (Constants.CUSTOMER_PROFILE_REGULAR != customer.getCustomerProfileId()) {
			if (Constants.CUSTOMER_TYPE_PERSONAL == customer.getCustomerTypeId()) {
				if (customer.getCustomerProfileId() != Constants.CUSTOMER_PROFILE_VIP) {
					return Mono.error(new Exception("Perfil erróneo para cliente Personal"));
				}
			} else if (Constants.CUSTOMER_TYPE_BUSINESS == customer.getCustomerTypeId()) {
				if (customer.getCustomerProfileId() != Constants.CUSTOMER_PROFILE_PYME) {
					return Mono.error(new Exception("Perfil erróneo para cliente Empresarial"));
				}
			}
		}

		return Mono.empty();
	}

	private Mono<Void> checkCustomerNotExistByDni(String dni) {
		return this.customerRepository.findBydni(dni)
				.flatMap(customer -> { 
					return Mono.error(new Exception("Customer with dni " + dni + " is already registered"));
				})
				.then();
	}
	
	@Override
	public Mono<Customer> create(Customer customer) {
		return checkCustomerNotExistByDni(customer.getDni())
				.mergeWith(checkCustomerProfile(customer))
				.then(customerRepository.save(customer));
	}

	@Override
	public Flux<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Mono<Customer> findById(String id) {
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
	public Mono<Void> delete(String id) {
		return customerRepository.deleteById(id);
	}
}
