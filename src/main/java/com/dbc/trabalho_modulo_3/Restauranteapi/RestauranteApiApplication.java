package com.dbc.trabalho_modulo_3.Restauranteapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RestauranteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauranteApiApplication.class, args);
	}

}
