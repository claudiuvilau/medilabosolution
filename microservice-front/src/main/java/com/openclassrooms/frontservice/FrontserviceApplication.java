package com.openclassrooms.frontservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.openclassrooms.frontservice")
public class FrontserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontserviceApplication.class, args);
	}

}
