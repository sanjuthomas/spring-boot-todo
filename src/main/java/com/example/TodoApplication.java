package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ml.MLConfiguration;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		MLConfiguration.loadConfigs();
		SpringApplication.run(TodoApplication.class, args);
	}
}
