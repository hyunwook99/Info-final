package com.samsam.info_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.samsam.info_final", "com.samsam.config"})
@EntityScan(basePackages = {"com.samsam.info_final.chatEntity" ,  "com.samsam.info_final.entity"})
public class InfoFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoFinalApplication.class, args);
	}

}
