package com.horizon.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class KnLoginController {


	@RequestMapping(value = { "/", "/dashboard**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		System.out.println("inside dashboard");

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is dashboard page!");
		model.setViewName("dashboard");
		return model;

	}

	@RequestMapping(value = {"/product" }, method = RequestMethod.GET)
	public ModelAndView product() {
		System.out.println("inside product");
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is product page!");
		model.setViewName("product");
		return model;

	}

	@RequestMapping(value = {"/service" }, method = RequestMethod.GET)
	public ModelAndView service() {
		System.out.println("inside service");
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is product page!");
		model.setViewName("service");
		return model;

	}

	@RequestMapping(value = {"/company" }, method = RequestMethod.GET)
	public ModelAndView company() {
		System.out.println("inside company");
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is company page!");
		model.setViewName("company");
		return model;

	}


	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");

		return model;

	}
	
	
	@RequestMapping(value = {"/testreq" }, method = RequestMethod.GET)
	public ModelAndView request() {
		System.out.println("inside request");
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is request page!");
		model.setViewName("request");
		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

}