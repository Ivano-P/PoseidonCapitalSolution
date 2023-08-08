package com.nnk.springboot.controllers;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.InvalidAddRatingException;
import com.nnk.springboot.exceptions.InvalidUpdateRatingException;
import com.nnk.springboot.services.RatingService;
import com.nnk.springboot.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import java.security.Principal;

@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class RatingController {

    private final RatingService ratingService;
    private final UserService userService;

    private static final String REDIRECT_RATING_LIST = "redirect:/rating/list";

    @RequestMapping("/rating/list")
    public String home(Model model, Principal principal) {
        log.info("home method called with: {}, {}", model, principal);
        model.addAttribute("ratings", ratingService.getAllRatings());
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));

        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating, Model model) {
        log.info("addRatingForm method called with: {}, {}", rating, model);

        model.addAttribute("rating", new Rating());

        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result) {
        log.info("validate method called with: {}, {}", rating, result);
        if(result.hasErrors()){
            log.error("Invalid rating");
            return "rating/add";
        }else{
            ratingService.saveRating(rating);
        }
        return REDIRECT_RATING_LIST;
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("showUpdateForm method called with: {}, {}", id, model);
        model.addAttribute("rating", ratingService.getRatingById(id));
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result) {
        log.info("updateRating method called with: {}, {}, {}", id, rating, result);
        if(result.hasErrors()){
            log.error("Invalid rating");
            return "rating/update";
        }else{
            ratingService.updateRating(rating, id);
        }
        return REDIRECT_RATING_LIST;
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id) {
        log.info("deleteRating method called with: {}", id);
        ratingService.deleteRatingById(id);
        return REDIRECT_RATING_LIST;
    }
}
