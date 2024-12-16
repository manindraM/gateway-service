package com.example.tmdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.tmdb")
public class TmdbApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmdbApiGatewayApplication.class, args);
	}

}
