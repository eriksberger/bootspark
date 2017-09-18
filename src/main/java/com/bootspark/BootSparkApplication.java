package com.bootspark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootSparkApplication  implements CommandLineRunner {

	@Autowired
	CountThemWords countThemWords;

	public static void main(String[] args) {
		SpringApplication.run(BootSparkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		countThemWords.count();
	}
}
