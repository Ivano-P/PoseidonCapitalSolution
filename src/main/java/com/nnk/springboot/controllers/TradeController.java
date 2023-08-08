package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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

@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class TradeController {

    private final UserService userService;
    private final TradeService tradeService;

    private static final String REDIRECT_TRADE_LIST = "redirect:/trade/list";
    @RequestMapping("/trade/list")
    public String home(Model model, Principal principal) {
        log.info("home method called with: {}, {}", model, principal);
        model.addAttribute("trades", tradeService.getAllTrades());
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTradeForm(Trade trade, Model model) {
        log.info("addTradeForm method called with: {}, {}", trade, model);
        model.addAttribute("trade", new Trade());
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result) {
        log.info("validate method called with: {}, {}", trade, result);
        if(result.hasErrors()){
            log.error("Invalid trade");
            return "trade/add";
        }else{
            tradeService.saveTrade(trade);
        }
        return REDIRECT_TRADE_LIST;
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("showUpdateForm method called with: {}, {}", id, model);
        model.addAttribute("trade", tradeService.getTradeById(id));
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result) {
        log.info("updateTrade method called with: {}, {}, {}", id, trade, result);
        if(result.hasErrors()){
            log.error("Invalid trade");
            return "trade/update";
        }else{
            tradeService.updateTrade(trade, id);
        }
        return REDIRECT_TRADE_LIST;
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id) {
        log.info("deleteTrade method called with: {}", id);
        tradeService.deleteTradeById(id);
        return REDIRECT_TRADE_LIST;
    }
}
