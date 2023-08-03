package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.InvalidAddCurvePointException;
import com.nnk.springboot.exceptions.InvalidUpdateCurvePointException;
import com.nnk.springboot.services.CurveService;
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
public class CurveController {

    private final CurveService curveService;
    private final UserService userService;

    private static final String REDIRECT_CURVE_POINT_LIST = "redirect:/curvePoint/list";

    @RequestMapping("/curvePoint/list")
    public String home(Model model, Principal principal) {
        log.info("home method called with: {}", model);

        model.addAttribute("curvePoints", curveService.getAllCurvePoints());
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));

        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint curvePoint, Model model) {
        log.info("addBidForm method called with: {}, {}", curvePoint, model);

        model.addAttribute("curvePoint", new CurvePoint());

        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result) {
        log.info("validate method called with: {}, {}", curvePoint, result);

        if(result.hasErrors()){
            throw new InvalidAddCurvePointException();
        }else{
            curveService.saveCurvePoint(curvePoint);
        }
        return REDIRECT_CURVE_POINT_LIST;
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        log.info("showUpdateForm method called with: {}, {}", id, model );

        model.addAttribute("curvePoint", curveService.getCurvePointById(id));

        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") int id, @Valid CurvePoint curvePoint,
                            BindingResult result) {
        log.info("updateBid method called with: {}, {}, {}", id, curvePoint, result);
        if(result.hasErrors()){
            throw new InvalidUpdateCurvePointException();
        }else{
            curveService.updateCurvePoint(curvePoint, id);
        }
        return REDIRECT_CURVE_POINT_LIST;
    }

    @PostMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        log.info("deleteBid method called with: {}", id);
        curveService.deleteCurvePointById(id);
        return REDIRECT_CURVE_POINT_LIST;
    }
}
