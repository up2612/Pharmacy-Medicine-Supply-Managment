package com.cognizant.medicalrepresentativeschedulemicroserice.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtil {
	public static LocalDate getDate(String scheduleStartDate) {

		LocalDate localDate = null;
		
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
			localDate = LocalDate.parse(scheduleStartDate, formatter);

			log.debug("localDate : {}", localDate);

		
		return localDate;
	}

}
