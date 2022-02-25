package com.nttdata.lagm.customer.springwebfluxcustomer.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.lagm.customer.springwebfluxcustomer.model.Customer;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, Long> {
}
