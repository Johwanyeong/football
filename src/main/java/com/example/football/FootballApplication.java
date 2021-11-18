package com.example.football;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.controller", "com.example.service", "com.example.security", "com.example.jwt"})
@EntityScan(basePackages = {"com.example.entity"})
@EnableJpaRepositories(basePackages={ "com.example.repository" })
public class FootballApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballApplication.class, args);
		System.out.println("start");
	}

}
