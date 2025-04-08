package com.example.narrative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class NarrativeApplication {

	// @Value("${Narrative}")
	String name;

	// @RestController
	class HelloController {
		
		String hello() {
			return "Hello , Welcome " + name + " ! " ;
		}
		
	}

	public static void main(String[] args) {
		SpringApplication.run(NarrativeApplication.class, args);
	}
}
