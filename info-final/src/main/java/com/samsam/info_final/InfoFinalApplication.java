package com.samsam.info_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.samsam.info_final", "com.samsam.config"})
public class InfoFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoFinalApplication.class, args);
	}

}
