package com.nttdata.lagm.customer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class AppConfig {
	@Value("${server.port}")
	private String port;
	
	@Value("${application.config.description}")
	private String profileDescription;
}
