package com.nnk.springboot.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@ControllerAdvice
public class DefaultExceptionHandler {

    private static final String ERROR_MESSAGE = "errorMsg";

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
}
