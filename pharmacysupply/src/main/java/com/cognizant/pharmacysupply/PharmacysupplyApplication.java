package com.cognizant.pharmacysupply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
public class PharmacysupplyApplication {

	
	public static void main(String[] args) {
		log.info("inside pharmacy supply application class");
		SpringApplication.run(PharmacysupplyApplication.class, args);
		log.info("execution completed for pharmacy supply application class");
	}

}
