package com.nnk.springboot.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@ControllerAdvice
public class DefaultExceptionHandler {

    private static final String ERROR_MESSAGE = "errorMsg";

    @ExceptionHandler(InvalidAddBidListException.class)
    public String handleInvalidAddBidListException(InvalidAddBidListException iable, RedirectAttributes redirectAttributes){
        log.error("InvalidAddBidListException thrown: {} " , iable.getMessage(), iable);
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, iable.getMessage());
        return "redirect:/bidList/add";
    }
    @ExceptionHandler(InvalidUpdateBidListException.class)
    public String handleInvalidUpdateBidListException(InvalidUpdateBidListException iuble, RedirectAttributes redirectAttributes){
        log.error("InvalidUpdateBidListException thrown: {} " , iuble.getMessage(), iuble);
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, iuble.getMessage());
        return "redirect:/bidList/update";
    }


    @ExceptionHandler(InvalidAddCurvePointException.class)
    public String handleInvalidAddCurvePointException(InvalidAddCurvePointException iacpe, RedirectAttributes redirectAttributes){
        log.error("InvalidAddCurvePointException thrown: {} " , iacpe.getMessage(), iacpe);
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, iacpe.getMessage());
        return "redirect:/curvePoint/add";
    }
    @ExceptionHandler(InvalidUpdateCurvePointException.class)
    public String handleInvalidUpdateCurvePointException(InvalidUpdateCurvePointException iucpe, RedirectAttributes redirectAttributes){
        log.error("InvalidUpdateCurvePointException thrown: {} " , iucpe.getMessage(), iucpe);
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, iucpe.getMessage());
        return "redirect:/curvePoint/update";
    }


    @ExceptionHandler(InvalidAddRatingException.class)
    public String handleInvalidAddRatingException(InvalidUpdateRatingException iare, RedirectAttributes redirectAttributes){
        log.error("InvalidAddRatingException thrown: {} " , iare.getMessage(), iare);
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, iare.getMessage());
        return "redirect:/rating/add";
    }

    @ExceptionHandler(InvalidUpdateRatingException.class)
    public String handleInvalidUpdateRatingException(InvalidUpdateRatingException iure, RedirectAttributes redirectAttributes){
        log.error("InvalidUpdateRatingException thrown: {} " , iure.getMessage(), iure);
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, iure.getMessage());
        return "redirect:/rating/update";
    }




    @ExceptionHandler(InvalidAddRuleNameException.class)
    public String handleInvalidAddRuleNameException(InvalidAddRuleNameException iarne, RedirectAttributes redirectAttributes){
        log.error("InvalidAddRuleNameException thrown: {} " , iarne.getMessage(), iarne);
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, iarne.getMessage());
        return "redirect:/ruleName/add";
    }
    @ExceptionHandler(InvalidUpdateRuleNameException.class)
    public String handleInvalidUpdateRuleNameException(InvalidUpdateRuleNameException iurne, RedirectAttributes redirectAttributes){
        log.error("InvalidUpdateRuleNameException thrown: {} " , iurne.getMessage(), iurne);
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, iurne.getMessage());
        return "redirect:/ruleName/update";
    }




    @ExceptionHandler(InvalidAddTradeException.class)
    public String handleInvalidAddTradeException(InvalidAddTradeException iate, RedirectAttributes redirectAttributes){
        log.error("InvalidAddTradeException thrown: {} " , iate.getMessage(), iate);
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, iate.getMessage());
        return "redirect:/trade/add";
    }
    @ExceptionHandler(InvalidUpdateTradeException.class)
    public String handleInvalidUpdateTradeException(InvalidUpdateTradeException iute, RedirectAttributes redirectAttributes){
        log.error("InvalidUpdateTradeException thrown: {} " , iute.getMessage(), iute);
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, iute.getMessage());
        return "redirect:/trade/update";
    }



    @ExceptionHandler(InvalidAddUserException.class)
    public String handleInvalidAddUserException(InvalidAddUserException iaue, RedirectAttributes redirectAttributes){
        log.error("InvalidAddUserException thrown: {} " , iaue.getMessage(), iaue);
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, iaue.getMessage());
        return "redirect:/user/add";
    }
    @ExceptionHandler(InvalidUpdateUserException.class)
    public String handleInvalidUpdateUserException(InvalidUpdateUserException iuue, RedirectAttributes redirectAttributes){
        log.error("InvalidUpdateUserException thrown: {} " , iuue.getMessage(), iuue);
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, iuue.getMessage());
        return "redirect:/user/update";
    }

}
