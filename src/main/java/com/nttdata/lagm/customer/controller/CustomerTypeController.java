package com.nttdata.lagm.customer.controller;

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

import com.nttdata.lagm.customer.model.CustomerType;
import com.nttdata.lagm.customer.service.CustomerTypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customerType")
public class CustomerTypeController {

    @Autowired
    private CustomerTypeService customerTypeService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    private void createCustomerType(@RequestBody CustomerType customerType) {
        customerTypeService.createCustomerType(customerType);
    }

    @GetMapping(value = "/get/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    private Flux<CustomerType> findAllCustomerType() {
        return customerTypeService.findAllCustomerType();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<Mono<CustomerType>> findCustomerTypeId(@PathVariable("id") Integer id) {
        Mono<CustomerType> monoCustomerType = customerTypeService.findByCustomerTypeId(id);
        return new ResponseEntity<>(monoCustomerType, monoCustomerType != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    private Mono<CustomerType> updateCustomerType(@RequestBody CustomerType customerType) {
    	return customerTypeService.updateCustomerType(customerType);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Mono<Void> deleteCustomerType(@PathVariable("id") Integer id) {
    	return customerTypeService.deleteCustomerType(id);
    }
}
