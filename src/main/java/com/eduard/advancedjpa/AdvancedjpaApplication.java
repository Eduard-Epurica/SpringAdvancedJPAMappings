package com.eduard.advancedjpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdvancedjpaApplication {

	public static void main(String[] args) {

		SpringApplication.run(AdvancedjpaApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(String[] args) {

		return runner -> {
			System.out.println("Hello world");
		};
	}

}



