package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Controller to manage User-related web operations.
 * Handles user listing, addition, update, and deletion.
 *
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@Controller
public class UserController {

    /** Service to interact with user-related business logic. */
    private final UserService userService;

    /** Redirect URL to the user listing page. */
    private static final String REDIRECT_USER_LIST = "redirect:/user/list";

    /**
     * Handles the listing of all users.
     *
     * @param model model to bind data to the view.
     * @param principal authenticated user.
     * @return the user listing view name.
     */
    @RequestMapping("/user/list")
    public String home(Model model, Principal principal) {
        log.info("home method called with: {}, {}", model, principal);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));
        return "user/list";
    }

    /**
     * Displays the form to add a new user.
     *
     * @param user an empty user object.
     * @param model model to bind data to the view.
     * @return the user addition form view name.
     */
    @GetMapping("/user/add")
    public String addUserForm(User user, Model model) {
        log.info("addUserForm method called with: {}, {}", user, model);
        model.addAttribute("user", new User());
        return "user/add";
    }

    /**
     * Validates and saves a new user.
     *
     * @param user the user data to save.
     * @param result validation result.
     * @param model model to bind data to the view.
     * @return redirect to the user listing if successful, otherwise, the user addition form.
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        log.info("validate method called with: {}, {}, {}", user, result,model);
        if (result.hasErrors()) {
            log.error("Invalid user");
            return "user/add";
        }else {
            userService.saveUser(user);
        }
        return REDIRECT_USER_LIST;
    }

    /**
     * Displays the form to update a user.
     *
     * @param id the ID of the user to update.
     * @param model model to bind data to the view.
     * @return the user update form view name.
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("showUpdateForm method called with: {}, {}", id, model);
        model.addAttribute("user", userService.getUserById(id));
        return "user/update";
    }


    /**
     * Validates and updates an existing user.
     *
     * @param id the ID of the user to update.
     * @param user the updated user data.
     * @param result validation result.
     * @return redirect to the user listing if successful, otherwise, the user update form.
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result ){
        log.info("updateUser method called with: {}, {}, {}", id, user, result);
        if (result.hasErrors()) {
            log.error("Invalid user");
            return "user/update";
        }else{
            userService.updateUser(user, id);
        }
        return REDIRECT_USER_LIST;
    }

    /**
     * Deletes a user by its ID.
     *
     * @param id the ID of the user to delete.
     * @return redirect to the user listing.
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        log.info("deleteUser method called with: {}", id);
        userService.deleteUserById(id);
        return REDIRECT_USER_LIST;
    }
}
