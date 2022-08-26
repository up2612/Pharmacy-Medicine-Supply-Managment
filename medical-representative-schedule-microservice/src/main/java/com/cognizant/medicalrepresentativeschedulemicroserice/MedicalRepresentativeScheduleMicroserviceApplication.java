package com.cognizant.medicalrepresentativeschedulemicroserice;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@EnableFeignClients
@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
public class MedicalRepresentativeScheduleMicroserviceApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(MedicalRepresentativeScheduleMicroserviceApplication.class, args);
		
	}

}
