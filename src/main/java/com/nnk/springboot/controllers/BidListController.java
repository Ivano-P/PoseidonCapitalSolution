package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
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
 * Controller responsible for handling CRUD operations on BidList entities.
 *
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class BidListController {

    /** Service to interact with BidList-related business logic. */
    private final BidListService bidListService;

    /** Service to interact with User-related business logic. */
    private final UserService userService;

    /** Redirect URL to the BidList listing page. */
    private static final String REDIRECT_BID_LIST_LIST = "redirect:/bidList/list";

    /**
     * Retrieves and displays the list of BidLists.
     *
     * @param model the model object for the view.
     * @param principal the authenticated user.
     * @return the name of the bid list view template.
     */
    @RequestMapping("/bidList/list")
    public String home(Model model, Principal principal) {
        log.info("home method called with : {}, {}", model, principal);

        model.addAttribute("bidLists", bidListService.getAllBids());
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));

        return "bidList/list";
    }

    /**
     * Displays the form for adding a new BidList.
     *
     * @param bid an empty bid object to bind form attributes.
     * @param principal the authenticated user.
     * @param model the model object for the view.
     * @return the name of the bid list add view template.
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid, Principal principal, Model model) {
        log.info("addBidForm called with bid:  {}, {}", bid, model);

        model.addAttribute("bidLists", new BidList());
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));

        return "bidList/add";
    }

    /**
     * Validates and saves a new BidList.
     *
     * @param bid the bid object to validate and save.
     * @param result contains any validation errors.
     * @return the redirect path depending on the validation result.
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result) {
        log.info("validate called with bid:  {}, {}", bid, result);

        if(result.hasErrors()){
            log.error("Invalid bid list");
            return "bidList/add";
        }else{
            bidListService.saveBidList(bid);
        }

        return REDIRECT_BID_LIST_LIST;
    }

    /**
     * Displays the form for updating an existing BidList.
     *
     * @param id the ID of the BidList to update.
     * @param model the model object for the view.
     * @return the name of the bid list update view template.
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("showUpdateForm method called with : {},  {}", id, model);

        BidList bidList = bidListService.getBidById(id);
        model.addAttribute("bidList", bidList );
        return "bidList/update";
    }

    /**
     * Validates and updates an existing BidList.
     *
     * @param id the ID of the BidList to update.
     * @param bidList the updated bid list object to validate and save.
     * @param result contains any validation errors.
     * @return the redirect path depending on the validation result.
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result) {
        log.info("updateBid method called with : {}, {}, {}", id, bidList, result);

        if(result.hasErrors()){
            log.error("Invalid bid list");
            return "bidList/update";
        }else{
            bidListService.updateBidList(bidList, id);
        }
        return REDIRECT_BID_LIST_LIST;
    }

    /**
     * Deletes a BidList by its ID.
     *
     * @param id the ID of the BidList to delete.
     * @return the redirect path to the bid list view.
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        log.info("deleteBid method called for bid id: {} ", id);
        bidListService.deleteBidListById(id);
        return REDIRECT_BID_LIST_LIST;
    }
}
