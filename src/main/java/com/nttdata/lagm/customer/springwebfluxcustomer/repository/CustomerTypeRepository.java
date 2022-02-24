package com.nttdata.lagm.customer.springwebfluxcustomer.repository;

import com.nttdata.lagm.customer.springwebfluxcustomer.model.CustomerType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTypeRepository extends ReactiveMongoRepository<CustomerType, Integer> {

}