package com.nttdata.lagm.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.lagm.customer.model.CustomerType;
import com.nttdata.lagm.customer.repository.CustomerTypeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    private Mono<Void> assertCustomerTypeNotExist(Integer id) {
    	return customerTypeRepository.findById(id)
    			.flatMap(customerType -> {
    				return Mono.error(new Exception("CustomerType with id " + id + " is already registered"));
    			})
    			.then();
    }
    
    @Override
    public Mono<CustomerType> createCustomerType(CustomerType customerType) {
        return assertCustomerTypeNotExist(customerType.getId())
        		.then(customerTypeRepository.save(customerType));
    }

    @Override
    public Mono<CustomerType> findByCustomerTypeId(Integer id) {
        return customerTypeRepository.findById(id);
    }

    @Override
    public Flux<CustomerType> findAllCustomerType() {
        return customerTypeRepository.findAll();
    }

    @Override
    public Mono<CustomerType> updateCustomerType(CustomerType customerType) {
        return customerTypeRepository.save(customerType);
    }

    @Override
    public Mono<Void> deleteCustomerType(Integer id) {
        return customerTypeRepository.deleteById(id);
    }


}
