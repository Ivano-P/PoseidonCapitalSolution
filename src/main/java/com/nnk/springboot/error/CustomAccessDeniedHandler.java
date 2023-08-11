package com.nnk.springboot.error;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * Custom implementation of the {@link AccessDeniedHandler} interface.
 * This handler is invoked when an authenticated user tries to access a resource they do not have permission for.
 * example when user with USER authority try to access page restricted for ADMIN Authority
 * It logs the unauthorized access attempt and redirects the user to custom access denied page.
 */
@Log4j2
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * Handles the access denied exception by logging the unauthorized access attempt
     * and redirecting the user to custom access denied page.
     *
     * @param request               The servlet request.
     * @param response              The servlet response.
     * @param accessDeniedException The exception thrown due to access denied error.
     * @throws IOException          If an input or output exception occurs.
     * @throws ServletException     If a servlet exception occurs.
     */
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            log.info("user " + authentication.getName() + " attempted to access: " + request.getRequestURL());
        }
        response.sendRedirect(request.getContextPath()+ "/access-denied");
    }
}
