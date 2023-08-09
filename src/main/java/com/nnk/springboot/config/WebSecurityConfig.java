package com.nnk.springboot.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

/**
 * Defines the configuration required for securing web requests in the application.
 * Implementations of this interface should provide specific configurations for
 * security aspects such as authentication, authorization, and session management.
 *
 * <p>This interface is crucial for integrating Spring Security into the application,
 * ensuring that specific endpoints are secured according to business requirements.</p>
 *
 */
@Service
public interface WebSecurityConfig {

    /**
     * Configures and returns the security filters to be used by the application.
     * This method should provide specific details about which endpoints require
     * authentication, which roles have access to specific endpoints, among other
     * security configurations.
     *
     */
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception;
}
