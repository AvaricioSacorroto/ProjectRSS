package com.rssproject.web.controllers;

import java.security.Principal;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginFormController {


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "action", required = false) final String action) {
		final ModelAndView modelAndView = new ModelAndView("login");
		System.out.println("ERROR:" + action);
		if (action != null) {
			modelAndView.addObject("error", "ERROR DE LOGIN");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied(final Principal user, final Locale locale) {

		final ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("message", user.getName());
		} else {
			model.addObject("message", "ACCESO DINERO");
		}
		model.setViewName("accessDenied");
		return model;
	}

}
