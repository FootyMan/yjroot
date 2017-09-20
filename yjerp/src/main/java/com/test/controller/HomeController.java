package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main() {
		return new ModelAndView("/index");
		
	}
	@RequestMapping(value = "/main/home", method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("/home");
		
	}
}
