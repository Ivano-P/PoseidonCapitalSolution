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

/**
 * Controller to manage Trade-related web operations.
 * Handles trade listing, addition, update, and deletion.
 *
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class TradeController {

    /** Service to interact with user-related business logic. */
    private final UserService userService;

    /** Service to interact with trade-related business logic. */
    private final TradeService tradeService;

    /** Redirect URL to the trade listing page. */
    private static final String REDIRECT_TRADE_LIST = "redirect:/trade/list";

    /**
     * Handles the listing of all trades.
     *
     * @param model model to bind data to the view.
     * @param principal authenticated user.
     * @return the trade listing view name.
     */
    @RequestMapping("/trade/list")
    public String home(Model model, Principal principal) {
        log.info("home method called with: {}, {}", model, principal);
        model.addAttribute("trades", tradeService.getAllTrades());
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));
        return "trade/list";
    }

    /**
     * Displays the form to add a new trade.
     *
     * @param trade an empty trade object.
     * @param model model to bind data to the view.
     * @return the trade addition form view name.
     */
    @GetMapping("/trade/add")
    public String addTradeForm(Trade trade, Model model) {
        log.info("addTradeForm method called with: {}, {}", trade, model);
        model.addAttribute("trade", new Trade());
        return "trade/add";
    }

    /**
     * Validates and saves a new trade.
     *
     * @param trade the trade data to save.
     * @param result validation result.
     * @return redirect to the trade listing if successful, otherwise, the trade addition form.
     */
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


    /**
     * Displays the form to update a trade.
     *
     * @param id the ID of the trade to update.
     * @param model model to bind data to the view.
     * @return the trade update form view name.
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("showUpdateForm method called with: {}, {}", id, model);
        model.addAttribute("trade", tradeService.getTradeById(id));
        return "trade/update";
    }

    /**
     * Validates and updates an existing trade.
     *
     * @param id the ID of the trade to update.
     * @param trade the updated trade data.
     * @param result validation result.
     * @return redirect to the trade listing if successful, otherwise, the trade update form.
     */
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

    /**
     * Deletes a trade by its ID.
     *
     * @param id the ID of the trade to delete.
     * @return redirect to the trade listing.
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id) {
        log.info("deleteTrade method called with: {}", id);
        tradeService.deleteTradeById(id);
        return REDIRECT_TRADE_LIST;
    }
}
