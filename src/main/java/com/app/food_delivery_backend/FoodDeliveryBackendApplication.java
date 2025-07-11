package com.app.food_delivery_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class FoodDeliveryBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryBackendApplication.class, args);
	}

}
