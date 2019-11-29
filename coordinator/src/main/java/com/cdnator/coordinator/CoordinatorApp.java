package com.cdnator.coordinator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CoordinatorApp {

	public static void main(String[] args) {
		SpringApplication.run(CoordinatorApp.class, args);
	}

	@Configuration
	@EnableWebMvc
	public class WebConfig implements WebMvcConfigurer {

		@Override
		public void addCorsMappings(CorsRegistry registry) {

			registry.addMapping("/coordinators/**")
				.allowedOrigins("http://localhost:8080")
				.allowedMethods("PUT", "PATCH", "DELETE", "POST", "GET")
				.allowCredentials(true).maxAge(3600);

			// Add more mappings...
		}
	}
}
