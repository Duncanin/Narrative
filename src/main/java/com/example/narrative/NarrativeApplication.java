package com.example.narrative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication (scanBasePackages="com.example.narrative")
@EntityScan(basePackages = "com.example.narrative.entity")
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
