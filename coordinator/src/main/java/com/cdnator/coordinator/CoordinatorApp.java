package com.cdnator.coordinator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class CoordinatorApp {

	public static void main(String[] args) {
		SpringApplication.run(CoordinatorApp.class, args);
	}

}
