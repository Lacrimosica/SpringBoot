package com.example.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumingRestApplication {
	
	//why declare logger this way:
	//private so that no other class can hijack your logger
	//static so there is only one logger instance per class, also avoiding attempts to serialize loggers.
	//final because there is no need to change the logger over the lifetime of the class
	//the parameter for getLogger is the name of the logger and naming it using the class name is a convention
	
	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

	
	//we use a RestTemplate to interact with another Rest API and bind the JSON to our custom domain type and process the incoming data basically.
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	
	//runs the RestTemplate (Consequently, fetches the quotation) ON STARTUP
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
										//takes the URL and the class it should bind to as arguments
		return args -> {				//use the getForObject method of the rest template that as arguments, 
			Quote quote = restTemplate.getForObject("https://quoters.apps.pcfone.io/api/random", Quote.class);
			log.info(quote.toString());
		};
	}
}

