package com.P001SpringBoot.back.usersmicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.P001SpringBoot.back.models.entity"})
public class UsersMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersMicroservicesApplication.class, args);
	}

}
