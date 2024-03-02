package com.openclassrooms.medilabosolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.openclassrooms.medilabosolution")
public class MedilabosolutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedilabosolutionApplication.class, args);
	}

}
