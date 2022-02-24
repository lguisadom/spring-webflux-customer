package com.nttdata.lagm.customer.springwebfluxcustomer.service;

import com.nttdata.lagm.customer.springwebfluxcustomer.model.CustomerType;
import com.nttdata.lagm.customer.springwebfluxcustomer.repository.CustomerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @Override
    public void createCustomerType(CustomerType customerType) {
        customerTypeRepository.save(customerType).subscribe();
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
