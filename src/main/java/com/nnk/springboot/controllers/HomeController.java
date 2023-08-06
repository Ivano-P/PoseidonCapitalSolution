package com.nnk.springboot.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
public class HomeController
{
	@RequestMapping("/")
	public String home(Model model) {
		log.info("home method called with  {}", model);
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		log.info("adminHome method called with  {}", model);
		return "redirect:/bidList/list";
	}


}
