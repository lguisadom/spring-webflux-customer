package com.nttdata.lagm.customer.repository;

import com.nttdata.lagm.customer.model.CustomerProfile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerProfileRepository extends ReactiveMongoRepository<CustomerProfile, Integer> {

}
