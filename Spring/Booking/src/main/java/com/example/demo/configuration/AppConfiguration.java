package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfiguration {
	
	@Bean
	public WebClient webclientconfigure() {
		return WebClient.builder().build();
	}
}

/*
 * @bean- create a object of third party service.
 * 
 * Webclient Interface - configuration  
 * 
 * return class, Interface, array. ( noo-primitive )
 * 
 * 
*/