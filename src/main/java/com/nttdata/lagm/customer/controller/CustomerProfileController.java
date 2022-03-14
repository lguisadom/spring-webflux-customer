package com.nttdata.lagm.customer.controller;

import com.nttdata.lagm.customer.model.CustomerProfile;
import com.nttdata.lagm.customer.service.CustomerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/customerProfile")
public class CustomerProfileController {
    @Autowired
    private CustomerProfileService customerProfileService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    private Flux<CustomerProfile> findAll() {
        return customerProfileService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<CustomerProfile> save(@RequestBody CustomerProfile customerProfile) {
        return customerProfileService.save(customerProfile);
    }
}
