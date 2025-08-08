package com.LocalService.LocalService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.LocalService.LocalService")

public class LocalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalServiceApplication.class, args);
	}

}
