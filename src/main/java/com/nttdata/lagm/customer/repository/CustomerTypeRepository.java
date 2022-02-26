package com.nttdata.lagm.customer.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.lagm.customer.model.CustomerType;

@Repository
public interface CustomerTypeRepository extends ReactiveMongoRepository<CustomerType, Integer> {

}