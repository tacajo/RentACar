package com.rentACarService;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableEurekaClient
public class RentACarServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(RentACarServiceApplication.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
