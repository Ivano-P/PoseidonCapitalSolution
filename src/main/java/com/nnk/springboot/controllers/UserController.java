package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.InvalidAddUserException;
import com.nnk.springboot.exceptions.InvalidUpdateUserException;
import com.nnk.springboot.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@Controller
public class UserController {

    private final UserService userService;

    private static final String REDIRECT_USER_LIST = "redirect:/user/list";

    @RequestMapping("/user/list")
    public String home(Model model, Principal principal) {
        log.info("home method called with: {}, {}", model, principal);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUserForm(User user, Model model) {
        log.info("addUserForm method called with: {}, {}", user, model);
        model.addAttribute("user", new User());
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        log.info("validate method called with: {}, {}, {}", user, result,model);
        if (result.hasErrors()) {
            throw new InvalidAddUserException();
        }else {
            userService.saveUser(user);
        }
        return REDIRECT_USER_LIST;
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("showUpdateForm method called with: {}, {}", id, model);
        model.addAttribute("user", userService.getUserById(id));
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        log.info("updateUser method called with: {}, {}, {}", id, user, result);
        if (result.hasErrors()) {
            throw new InvalidUpdateUserException();
        }else{
            userService.updateUser(user, id);
        }
        return REDIRECT_USER_LIST;
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        log.info("deleteUser method called with: {}", id);
        userService.deleteUserById(id);
        return REDIRECT_USER_LIST;
    }
}
