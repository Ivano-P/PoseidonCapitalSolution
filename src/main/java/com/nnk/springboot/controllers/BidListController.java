package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.InvalidAddBidListException;
import com.nnk.springboot.exceptions.InvalidUpdateBidListException;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.services.UserService;
import jakarta.validation.Valid;
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

import java.security.Principal;

@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class BidListController {

    private final BidListService bidListService;
    private final UserService userService;
    private static final String REDIRECT_BID_LIST_LIST = "redirect:/bidList/list";

    @RequestMapping("/bidList/list")
    public String home(Model model, Principal principal) {
        log.info("home method called with : {}, {}", model, principal);

        model.addAttribute("bidLists", bidListService.getAllBids());
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));

        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid, Principal principal, Model model) {
        log.info("addBidForm called with bid:  {}, {}", bid, model);

        model.addAttribute("bidLists", new BidList());
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));

        return "bidList/add";
    }

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

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("showUpdateForm method called with : {},  {}", id, model);

        BidList bidList = bidListService.getBidById(id);
        model.addAttribute("bidList", bidList );
        return "bidList/update";
    }

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

    @PostMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        log.info("deleteBid method called for bid id: {} ", id);
        bidListService.deleteBidListById(id);
        return REDIRECT_BID_LIST_LIST;
    }
}
