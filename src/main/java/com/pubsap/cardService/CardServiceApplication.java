package com.pubsap.cardService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@ComponentScan(basePackages = "com.pubsap.cardService")
@SpringBootApplication
public class CardServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CardServiceApplication.class, args);
	}
}
