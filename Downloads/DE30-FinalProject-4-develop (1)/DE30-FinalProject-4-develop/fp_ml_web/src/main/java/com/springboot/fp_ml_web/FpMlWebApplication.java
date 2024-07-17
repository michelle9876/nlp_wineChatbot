package com.springboot.fp_ml_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FpMlWebApplication {
	public static void main(String[] args) {
			SpringApplication.run(FpMlWebApplication.class, args);
		}
}
