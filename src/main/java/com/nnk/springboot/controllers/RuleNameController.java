package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.InvalidAddRuleNameException;
import com.nnk.springboot.exceptions.InvalidUpdateRuleNameException;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.services.RuleNameService;
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
public class RuleNameController {

    private final RuleNameService ruleNameService;
    private final UserService userService;

    private static final String REDIRECT_RULE_NAME_LIST = "redirect:/ruleName/list";

    @RequestMapping("/ruleName/list")
    public String home(Model model, Principal principal) {
        log.info("home method called with: {}, {}", model, principal);
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName, Model model) {
        log.info("addRatingForm method called with: {}, {}", ruleName, model);
        model.addAttribute("ruleName", new RuleName());
        return "ruleName/add";
    }

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

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("showUpdateForm method called with: {}, {}", id, model);
        model.addAttribute("ruleName", ruleNameService.getRuleNameById(id));
        return "ruleName/update";
    }

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

    @PostMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id) {
        log.info("deleteRuleName method called with: {}", id);
        ruleNameService.deleteRuleNameById(id);
        return REDIRECT_RULE_NAME_LIST;
    }
}
