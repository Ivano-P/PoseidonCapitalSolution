package com.nnk.springboot.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller responsible for user login operations.
 *
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class LoginController{

    /**
     * Presents the login form.
     *
     * @return the login view.
     */
    @GetMapping("login")
    public ModelAndView login() {
        log.info("login method called");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

}
