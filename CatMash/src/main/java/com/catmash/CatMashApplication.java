package com.catmash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CatMashApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatMashApplication.class, args);
	}

}
