package com.example.orm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;

@SpringBootApplication
public class OrmApplication {

	CrudRepository c;

	public static void main(String[] args) {
		SpringApplication.run(OrmApplication.class, args);
	}

}
