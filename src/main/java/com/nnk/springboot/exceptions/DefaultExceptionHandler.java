package com.nnk.springboot.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@ControllerAdvice
public class DefaultExceptionHandler {

    private static final String ERROR_MESSAGE = "errorMsg";
    @ExceptionHandler(InvalidBidListException.class)
    public String handleInvalidBidListException(InvalidBidListException ible, RedirectAttributes redirectAttributes){
        log.error("InvalidBidListException thrown: {} " , ible.getMessage(), ible);
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, ible.getMessage());
        return "redirect:/bidList/list";
    }


}
