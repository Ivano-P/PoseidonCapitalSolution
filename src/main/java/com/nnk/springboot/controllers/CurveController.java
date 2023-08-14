package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurveService;
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
 * Controller responsible for handling CRUD operations on CurvePoint entities.
 *
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class CurveController {

    /** Service to interact with CurvePoint-related business logic. */
    private final CurveService curveService;

    /** Service to interact with User-related business logic. */
    private final UserService userService;

    /** Redirect URL to the CurvePoint listing page. */
    private static final String REDIRECT_CURVE_POINT_LIST = "redirect:/curvePoint/list";

    /**
     * Retrieves and displays the list of CurvePoints.
     *
     * @param model the model object for the view.
     * @param principal the authenticated user.
     * @return the name of the curve point list view template.
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model, Principal principal) {
        log.info("home method called with: {}", model);

        model.addAttribute("curvePoints", curveService.getAllCurvePoints());
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));

        return "curvePoint/list";
    }

    /**
     * Displays the form for adding a new CurvePoint.
     *
     * @param curvePoint an empty curve point object to bind form attributes.
     * @param model the model object for the view.
     * @return the name of the curve point add view template.
     */
    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint curvePoint, Model model) {
        log.info("addBidForm method called with: {}, {}", curvePoint, model);

        model.addAttribute("curvePoint", new CurvePoint());

        return "curvePoint/add";
    }

    /**
     * Validates and saves a new CurvePoint.
     *
     * @param curvePoint the curve point object to validate and save.
     * @param result contains any validation errors.
     * @return the redirect path depending on the validation result.
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result) {
        log.info("validate method called with: {}, {}", curvePoint, result);

        if(result.hasErrors()){
            log.error("Invalid curve point");
            return "curvePoint/add";
        }else{
            curveService.saveCurvePoint(curvePoint);
        }
        return REDIRECT_CURVE_POINT_LIST;
    }

    /**
     * Displays the form for updating an existing CurvePoint.
     *
     * @param id the ID of the CurvePoint to update.
     * @param model the model object for the view.
     * @return the name of the curve point update view template.
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        log.info("showUpdateForm method called with: {}, {}", id, model );

        model.addAttribute("curvePoint", curveService.getCurvePointById(id));

        return "curvePoint/update";
    }

    /**
     * Validates and updates an existing CurvePoint.
     *
     * @param id the ID of the CurvePoint to update.
     * @param curvePoint the updated curve point object to validate and save.
     * @param result contains any validation errors.
     * @return the redirect path depending on the validation result.
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") int id, @Valid CurvePoint curvePoint,
                            BindingResult result) {
        log.info("updateBid method called with: {}, {}, {}", id, curvePoint, result);
        if(result.hasErrors()){
            log.error("Invalid curve point");
            return "curvePoint/update";
        }else{
            curveService.updateCurvePoint(curvePoint, id);
        }
        return REDIRECT_CURVE_POINT_LIST;
    }

    /**
     * Deletes a CurvePoint by its ID.
     *
     * @param id the ID of the CurvePoint to delete.
     * @return the redirect path to the curve point list view.
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        log.info("deleteBid method called with: {}", id);
        curveService.deleteCurvePointById(id);
        return REDIRECT_CURVE_POINT_LIST;
    }
}
