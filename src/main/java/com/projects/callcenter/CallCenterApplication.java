package com.projects.callcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class CallCenterApplication {
	public static void main(String[] args) {
		SpringApplication.run(CallCenterApplication.class, args);
	}
}
