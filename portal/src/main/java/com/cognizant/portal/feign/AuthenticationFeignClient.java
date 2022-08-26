package com.cognizant.portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.portal.model.UserLoginCredential;
import com.cognizant.portal.model.UserToken;



@FeignClient(url = "http://localhost:8084/auth/api/v1", name = "Authorizatiion-Microservice")
public interface AuthenticationFeignClient {

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserLoginCredential authenticationRequest);

	@GetMapping(path = "/authorize")
	public boolean verifyToken(@RequestHeader(name = "Authorization", required = true) String token);

}
