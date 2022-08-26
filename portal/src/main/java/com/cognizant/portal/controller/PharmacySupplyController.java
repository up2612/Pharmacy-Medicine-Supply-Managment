package com.cognizant.portal.controller;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.portal.exception.MedicineNotFoundException;
import com.cognizant.portal.feign.PharmacySupplyFeignClient;
import com.cognizant.portal.model.MedicineDemand;
import com.cognizant.portal.model.MedicineSupply;
import feign.FeignException.FeignClientException;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/user")
@Controller
public class PharmacySupplyController {

	
	@Autowired
	private PharmacySupplyFeignClient feignClient;

	List<MedicineSupply> medicineSupplyList = new ArrayList<>();


	@SuppressWarnings("unchecked")
	@RequestMapping("/getMedicineSupply")
	public ModelAndView getMedicineSupply(@ModelAttribute("medicineDemand") MedicineDemand medicineDemand,
			HttpServletRequest session) throws Exception {

		log.info("Inside Pharamcy Portal getmedicinesupply count controller");

		ModelAndView modelAndView = new ModelAndView();
		String token=(String) session.getSession().getAttribute("Authorization");
		List<MedicineDemand> medicineDemandList = new ArrayList<>();
		medicineDemandList.add(medicineDemand);
		log.debug("medicineDemand{} :", medicineDemand);

		ResponseEntity<?> response = null;
		try {
			response = feignClient.getPharmacySupply( medicineDemandList,token);
			log.debug("response{}:", response);
		} catch (FeignClientException e) {
			modelAndView.setViewName("medicineNotFound");
			response= new ResponseEntity<>("medicineNotFound", HttpStatus.NOT_FOUND);
		}
		
		HttpStatus statusCode = response.getStatusCode();
		
		if (statusCode == HttpStatus.NOT_FOUND) {
			modelAndView.addObject("error", true);
		}
		
		if (response.getBody() instanceof String) {
			throw new MedicineNotFoundException("Medicine not found");
		}
		
		log.debug("response atik{}:", response);

		medicineSupplyList = (List<MedicineSupply>) response.getBody();
		
		log.debug("medicineSupplyList{}:", medicineSupplyList);
		

		modelAndView.setViewName("medicineSupplyList");
		modelAndView.addObject("medicineSupplyList", medicineSupplyList);
		log.info(modelAndView.toString());
		return modelAndView;
	}

	@RequestMapping("/showMedicineSupply")
	public ModelAndView showMedicineSupply(HttpServletRequest session) {
		String token=(String) session.getSession().getAttribute("Authorization");

		@SuppressWarnings("unchecked")
		List<MedicineSupply> medicineSupply = feignClient.getMedicineSupply(token);
		ModelAndView mv = new ModelAndView("medicineSupplyList");
		System.out.println(medicineSupplyList.size());
		mv.addObject("medicineSupplyList", medicineSupply);
		return mv;
	}

	@RequestMapping("/medicineDemand")
	public String getMedicineDemandPage(@ModelAttribute("medicineDemand") MedicineDemand medicineDemand) {
		log.info("Start");
		return "medicineDemand";
	}


	@RequestMapping("/showMedicineDemand")
	public ModelAndView getMedicineDemandList(@ModelAttribute("medicineDemand") MedicineDemand medicineDemand,
			HttpServletRequest session) {
		log.info("Start");
		String token=(String) session.getSession().getAttribute("Authorization");

		@SuppressWarnings("unchecked")
		List<MedicineDemand> medicineDemandList = (List<MedicineDemand>) feignClient.getMedicineDemand(token)
				.getBody();
		ModelAndView mv = new ModelAndView("showMedicineDemand");
		mv.addObject("medicineDemandList", medicineDemandList);
		log.debug("medicineDemandList{}:", medicineDemandList);
		return mv;
	}

}
