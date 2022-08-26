package com.cognizant.portal.controller;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cognizant.portal.feign.AuthenticationFeignClient;
import com.cognizant.portal.model.UserLoginCredential;

@Controller
@RequestMapping("/user")
@SessionAttributes("username")
public class LoginController {

	
	@Autowired
	private AuthenticationFeignClient client;
	
	

	/**
	 * @param user
	 * @param model
	 * @return login view
	 */
	@GetMapping(value = "/login")
	public String showLoginPage(@ModelAttribute("user") UserLoginCredential user, Model model) {
		return "login";
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 */
	/*@GetMapping(value = "/logout")
	public String logoutAndShowLoginPage(Model model, HttpServletRequest request) {
		/*
		 * set session as invalidate 
		 * set username to null
		 
		request.getSession().invalidate();
		model.addAttribute("username", null);
		return "redirect:/user/login";
	}*/

@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("token", null);
		return "logout";
	}

	@PostMapping(value = "/login")
	public String afterLoginAuthenticateAndRedirect(@ModelAttribute("user") UserLoginCredential user, Model model,
			HttpServletRequest request) {
		/*
		 * call authentication microservice client
		 * generate the token
		 * if excepyions occured while generating token, redirect to same view
		 * otherwise return welcome view
		 */
		System.out.println(user.getUserName()+user.getPassword());
		ResponseEntity<?> responseGenerated = null;
		try {
			
			responseGenerated = client.createAuthenticationToken(user);
			

		} catch (Exception e) {
			System.out.println(e.getMessage()+"=========================");
			e.printStackTrace();
			model.addAttribute("errorMessage", "Invalid Credentials");
			return "login";
		}
		/*
		 * retreive jwt token from map set it to session
		 */
		@SuppressWarnings("unchecked")
		Map<String, String> tokenMap = (Map<String, String>) responseGenerated.getBody();
		String token = tokenMap.get("token");
		
		request.getSession().setAttribute("Authorization", "Bearer " + token);
		request.getSession().setAttribute("userName", user.getUserName());
		return "homepage";
	}
	@GetMapping("/home")
	public String home() {

		
		return "homepage";
	}
}


/*import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.portal.feign.AuthenticationFeignClient;
import com.cognizant.portal.model.UserLoginCredential;
import com.cognizant.portal.model.UserToken;
import com.cognizant.portal.service.AuthFeignService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/user")
public class LoginController {



	@Autowired
	private AuthFeignService feignService;
	@Autowired
	AuthenticationFeignClient fin;

	@GetMapping("/login")
	public ModelAndView userLogin(@ModelAttribute("usercredentials") UserLoginCredential usercredentials,
			BindingResult bindingresult) {

		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("loginMessage", "Login");
		return modelAndView;
	}

	@GetMapping("/home")
	public String home() {

		
		return "homepage";
	}


	@PostMapping("/homepage")
	public ModelAndView userLogin(@ModelAttribute("usercredentials") UserLoginCredential usercredentials,
			BindingResult bindingresult, HttpSession session) {

		log.debug("username{}: ", usercredentials.getUserid());
		ResponseEntity<?> response = null;

		try {
			log.info("inside tryyy");
			//response = feignService.getToken(usercredentials);
			response=fin.login(usercredentials);
		} catch (Exception e) {
			log.error("Invalid credentials");
			ModelAndView modelAndView = new ModelAndView("login");
			modelAndView.addObject("loginMessage", "Bad Credentials");
			return modelAndView;
		}

		log.debug("Response{}: ", response);
		log.info("Getting body from response entity");

		UserToken userToken = (UserToken) response.getBody();

		log.debug("token{}:", userToken.getAuthToken());
		log.debug("userToken{}: ", userToken);

		session.setAttribute("token", userToken.getAuthToken());

		log.debug("session{}:", session.toString());

		ModelAndView modelAndView = new ModelAndView("homepage");
		return modelAndView;

	}


	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("token", null);
		return "logout";
	}

}*/
