package com.rssproject.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rssproject.api.modelo.Feed;
import com.rssproject.api.services.UsuarioService;
import com.rssproject.web.forms.UrlForm;
import com.rssproject.web.security.CustomUser;
import com.rssproject.web.validation.UrlFormValidator;

@Controller
public class RssController {
	@Autowired
	UsuarioService usuarioService;

	@Autowired
	UrlFormValidator urlFormValidator;

	Logger LOGGER = Logger.getLogger(RssController.class);

	@InitBinder(value = "urlForm")
	protected void dataBinding(final WebDataBinder binder) {
		binder.addValidators(urlFormValidator);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView rrs() {
		ModelAndView modelAndView = new ModelAndView("redirect:/rrs");

		return modelAndView;
	}
	@RequestMapping(value = "/rrs", method = RequestMethod.GET)
	public ModelAndView verRRS(@ModelAttribute("urlForm") final UrlForm urlForm) {
		ModelAndView modelAndView = new ModelAndView("rrs");
		modelAndView.addObject("urlform", urlForm);
		final CustomUser currentUser = getCurrentUser();
		List<Feed> feeds = usuarioService.getFeedFromId(currentUser.getId());
		modelAndView.addObject("feeds", feeds);
		modelAndView.addObject("currentUser", currentUser.getId());
		return modelAndView;
	}

	@RequestMapping(value = "/rrs", method = RequestMethod.POST, params = { "action=add" })
	public ModelAndView addRRS(@Valid @ModelAttribute("urlForm") final UrlForm urlForm,
			final BindingResult bindingResult) {
		LOGGER.debug("URLFROM ID:" + urlForm.getId());
		LOGGER.debug("VALIDATE: has error?");
		if (bindingResult.hasErrors()) {
			LOGGER.debug("VALIDATE: has error");
			return verRRS(urlForm);
		}

		LOGGER.debug("URLFROM:" + urlForm.getUrl());
		LOGGER.debug("URLFROM:" + urlForm.getId());
		usuarioService.addURL(getCurrentUser().getId(), urlForm);
		return verRRS(urlForm);
	}

	private CustomUser getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return (CustomUser) auth.getPrincipal();
	}


}
