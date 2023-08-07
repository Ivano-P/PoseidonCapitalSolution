package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import lombok.AllArgsConstructor;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class CustomErrorController implements ErrorController {

    private final UserService userService;
    private static final String ACCESS_DENIED_MESSAGE = "You must be an administrator to access this page";
    @RequestMapping("/error")
    public String handleError(Model model, Principal principal) {
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));
        model.addAttribute("errorMsg", ACCESS_DENIED_MESSAGE);
        return "403";
    }

}
