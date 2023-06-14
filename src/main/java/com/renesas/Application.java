package com.renesas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
* Application class is the main entry point of this SpringBoot Application. 
* Execute the main method to run the application on in-memory Tomcat Server and H2 database.
* 
*/
@SpringBootApplication
public class Application {
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
