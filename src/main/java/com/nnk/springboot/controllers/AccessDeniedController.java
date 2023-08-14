package com.nnk.springboot.controllers;

import com.nnk.springboot.error.CustomAccessDeniedHandler;
import com.nnk.springboot.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * Controller responsible for handling access denied scenarios.
 *
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class AccessDeniedController {

    /** Service to interact with User-related business logic. */
    private final UserService userService;

    /** Error message that appears on custom 403 error page */
    private static final String ACCESS_DENIED_MESSAGE = "You must be an administrator to access this page";

    /**
     * Handles the access denied error and displays a custom error message.
     *
     * @param model the model object for the view.
     * @param principal the authenticated user.
     * @return the name of the 403 error view template.
     */
    @GetMapping("/access-denied")
    public String handleError(Model model, Principal principal) {
        log.error("handleError method called with: {}, {}", model, principal);
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));
        model.addAttribute("errorMsg", ACCESS_DENIED_MESSAGE);
        return "403";
    }

    /**
     * Provides custom access denied handler bean.
     *
     * @return an instance of the custom access denied handler.
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {return new CustomAccessDeniedHandler();}
}
