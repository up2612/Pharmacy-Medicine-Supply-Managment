package com.cognizant.portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "${schedule.url}", name = "repschedule")
public interface RepresentativeFeignClient {

	@GetMapping("/api/v1/repschedule/{schedulestartdate}")
	public ResponseEntity<?> getRepSchedule(@RequestHeader(name = "Authorization") String token,
			@DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("schedulestartdate") String schedulestartdate);

}
