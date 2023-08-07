package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


//@RequestMapping("app")
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class LoginController {

    private final UserRepository userRepository;

    @GetMapping("login")
    public ModelAndView login() {
        log.info("login method called");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        log.info("getAllUserArticles method called");
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    /*
    @GetMapping("/error")
    public ModelAndView error(Model model, Principal principal) {
        log.info("error method called with {}, {}", model, principal);
        ModelAndView mav = new ModelAndView();
        model.addAttribute("currentUser", userRepository.findByUsername(principal.getName()));
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }

     */
}
