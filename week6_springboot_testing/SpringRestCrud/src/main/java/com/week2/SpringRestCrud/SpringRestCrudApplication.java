package com.week2.SpringRestCrud;

import com.week2.SpringRestCrud.services.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringRestCrudApplication implements CommandLineRunner {

	//private final DataService dataService;

	public static void main(String[] args) {
		SpringApplication.run(SpringRestCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println(dataService.getData());
	}
}
