package com.nnk.springboot.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller responsible for handling home route and admin home route operations.
 *
 */
@Log4j2
@Controller
public class HomeController {

	/**
	 * Displays the home page.
	 *
	 * @param model the model object for the view.
	 * @return the name of the home view template.
	 */
	@RequestMapping("/")
	public String home(Model model) {
		log.info("home method called with  {}", model);
		return "/home";
	}

	/**
	 * Redirects the admin to the bid list after successful login.
	 *
	 * @param model the model object for the view.
	 * @return the redirect path to the bid list.
	 */
	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		log.info("adminHome method called with  {}", model);
		return "redirect:/bidList/list";
	}


}
