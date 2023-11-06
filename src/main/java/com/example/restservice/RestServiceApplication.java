package com.example.restservice;

import com.example.restservice.utilities.DemoLogging;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext theThing = SpringApplication.run(RestServiceApplication.class, args);

		DemoLogging.runDemoLogging();
	}
}
