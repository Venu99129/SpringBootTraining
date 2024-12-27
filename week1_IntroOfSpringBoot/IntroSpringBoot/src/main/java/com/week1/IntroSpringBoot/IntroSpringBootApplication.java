package com.week1.IntroSpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntroSpringBootApplication implements CommandLineRunner {

	@Autowired
	Apple apple1;

	@Autowired
	Apple apple2;

	@Autowired
	DBService dbService;

	public static void main(String[] args) {
		SpringApplication.run(IntroSpringBootApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {
//		apple1.eatApple();
//		apple2.eatApple();

		dbService.getDBData();

	}
}
