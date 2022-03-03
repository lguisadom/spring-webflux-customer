package com.nttdata.lagm.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringWebfluxCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxCustomerApplication.class, args);
	}

}
