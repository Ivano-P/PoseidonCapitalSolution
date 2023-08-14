package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import com.nnk.springboot.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.security.Principal;

/**
 * Controller responsible for managing Rating-related web operations.
 * This includes listing, adding, updating, and deleting ratings.
 *
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class RatingController {

    /** Service to interact with rating-related business logic. */
    private final RatingService ratingService;

    /** Service to interact with user-related business logic. */
    private final UserService userService;

    /** Redirect URL to the rating listing page. */
    private static final String REDIRECT_RATING_LIST = "redirect:/rating/list";

    /**
     * Displays a list of all ratings.
     *
     * @param model model to bind data to the view.
     * @param principal authenticated user.
     * @return the rating listing view name.
     */
    @RequestMapping("/rating/list")
    public String home(Model model, Principal principal) {
        log.info("home method called with: {}, {}", model, principal);
        model.addAttribute("ratings", ratingService.getAllRatings());
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));

        return "rating/list";
    }

    /**
     * Presents the form to add a new rating.
     *
     * @param rating an empty rating object.
     * @param model model to bind data to the view.
     * @return the rating addition form view name.
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating, Model model) {
        log.info("addRatingForm method called with: {}, {}", rating, model);

        model.addAttribute("rating", new Rating());

        return "rating/add";
    }

    /**
     * Validates and saves a new rating.
     *
     * @param rating the rating data to save.
     * @param result validation result.
     * @return redirect to the rating listing if successful, otherwise, the rating addition form.
     */
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

    /**
     * Displays the form to update an existing rating.
     *
     * @param id the ID of the rating to update.
     * @param model model to bind data to the view.
     * @return the rating update form view name.
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("showUpdateForm method called with: {}, {}", id, model);
        model.addAttribute("rating", ratingService.getRatingById(id));
        return "rating/update";
    }

    /**
     * Validates and updates an existing rating.
     *
     * @param id the ID of the rating to update.
     * @param rating the updated rating data.
     * @param result validation result.
     * @return redirect to the rating listing if successful, otherwise, the rating update form.
     */
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

    /**
     * Deletes a rating based on its ID.
     *
     * @param id the ID of the rating to delete.
     * @return redirect to the rating listing.
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id) {
        log.info("deleteRating method called with: {}", id);
        ratingService.deleteRatingById(id);
        return REDIRECT_RATING_LIST;
    }
}
