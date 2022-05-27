package com.gameofthrones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GameOfThronesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameOfThronesApplication.class, args);
	}

}
