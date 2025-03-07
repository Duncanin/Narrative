package com.example.narrative;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
public class NarrativeApplication {

	@Value("${Narrative}")
	String name;

	@RestController
	class HelloController {
		@GetMapping("/")
		String hello() {
			return "Hello , Welcome " + name + " ! " ;
		}
		
		
	}

	public static void main(String[] args) {
		SpringApplication.run(NarrativeApplication.class, args);
	}
}
