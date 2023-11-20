package com.example.restservice;

import com.example.restservice.utilities.DemoLogging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RestServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RestServiceApplication.class, args);

		//TODO:  This is a test of the logging system.  Remove it when done.
		DemoLogging.runDemoLogging();
	}
}
