package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
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
 * Controller to manage RuleName-related web operations.
 * Handles rule name listing, addition, update, and deletion.
 *
 * @author [Your Name]
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class RuleNameController {

    /** Service to interact with rule name-related business logic. */
    private final RuleNameService ruleNameService;

    /** Service to interact with user-related business logic. */
    private final UserService userService;

    /** Redirect URL to the rule name listing page. */
    private static final String REDIRECT_RULE_NAME_LIST = "redirect:/ruleName/list";

    /**
     * Handles the listing of all rule names.
     *
     * @param model model to bind data to the view.
     * @param principal authenticated user.
     * @return the rule name listing view name.
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model, Principal principal) {
        log.info("home method called with: {}, {}", model, principal);
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));
        return "ruleName/list";
    }

    /**
     * Displays the form to add a new rule name.
     *
     * @param ruleName an empty rule name object.
     * @param model model to bind data to the view.
     * @return the rule name addition form view name.
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName, Model model) {
        log.info("addRatingForm method called with: {}, {}", ruleName, model);
        model.addAttribute("ruleName", new RuleName());

        return "ruleName/add";
    }

    /**
     * Validates and saves a new rule name.
     *
     * @param ruleName the rule name data to save.
     * @param result validation result.
     * @return redirect to the rule name listing if successful, otherwise, the rule name addition form.
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result) {
        log.info("validate method called with: {}, {}", ruleName, result);
        if(result.hasErrors()){
            log.error("Invalid rule");
            return "ruleName/add";
        }else{
            ruleNameService.saveRuleName(ruleName);
        }
        return REDIRECT_RULE_NAME_LIST;
    }

    /**
     * Displays the form to update a rule name.
     *
     * @param id the ID of the rule name to update.
     * @param model model to bind data to the view.
     * @return the rule name update form view name.
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("showUpdateForm method called with: {}, {}", id, model);
        model.addAttribute("ruleName", ruleNameService.getRuleNameById(id));
        return "ruleName/update";
    }

    /**
     * Validates and updates an existing rule name.
     *
     * @param id the ID of the rule name to update.
     * @param ruleName the updated rule name data.
     * @param result validation result.
     * @return redirect to the rule name listing if successful, otherwise, the rule name update form.
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result) {
        log.info("updateRuleName method called with: {}, {}, {}", id, ruleName, result);
        if(result.hasErrors()){
            log.error("Invalid rule");
            return "ruleName/update";
        }else{
            ruleNameService.updateRuleName(ruleName, id);
        }
        return REDIRECT_RULE_NAME_LIST;
    }

    /**
     * Deletes a rule name by its ID.
     *
     * @param id the ID of the rule name to delete.
     * @return redirect to the rule name listing.
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id) {
        log.info("deleteRuleName method called with: {}", id);
        ruleNameService.deleteRuleNameById(id);
        return REDIRECT_RULE_NAME_LIST;
    }
}
