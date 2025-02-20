package com.example.synchronizertoken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SynchronizerTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SynchronizerTokenApplication.class, args);
	}

}
