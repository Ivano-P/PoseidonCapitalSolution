package com.nnk.springboot.implementations;

import com.nnk.springboot.config.WebSecurityConfig;
import com.nnk.springboot.error.CustomAccessDeniedHandler;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 * This class provides the implementation for web security configurations.
 * </br>
 * It sets up configurations for request authorization, login behavior, and session management.
 *
 * @author Ivano PETTY
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Configuration
@EnableWebSecurity
public class WebSecurityConfigImpl implements WebSecurityConfig {

    /**
     * Service responsible for user details like username, full name, password and role.
     */
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Provides functionality for examination of handler mappings, in this case it's used to creat MvcRequestMatcher
     */
    private final HandlerMappingIntrospector handlerMappingIntrospector;

    /**
     * Configures and returns a {@link SecurityFilterChain} bean,
     * which encapsulates the original Spring Security filter chain.
     * <p>
     * This method sets up MVC matchers for paths,
     * specifies authorization requirements for different request patterns,
     * and configures login and logout behaviors.
     * </p>
     *
     * @param httpSecurity Used for building custom security configurations.
     * @return A built {@link SecurityFilterChain} with the specified configurations.
     * @throws Exception if there's an error during the configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        log.info("securityFilterChain method called with: {}", httpSecurity );
        //MVC match for path that doesn't need authentication
        MvcRequestMatcher userListMatcher = new MvcRequestMatcher(handlerMappingIntrospector,
                "/user/list");
        MvcRequestMatcher userAddMatcher = new MvcRequestMatcher(handlerMappingIntrospector,
                "/user/add");

        MvcRequestMatcher userUpdateMatcher = new MvcRequestMatcher(handlerMappingIntrospector,
                "/user/update");

        MvcRequestMatcher homeMatcher = new MvcRequestMatcher(handlerMappingIntrospector,
                "/home");

        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(homeMatcher).permitAll()
                        .requestMatchers(userListMatcher, userAddMatcher, userUpdateMatcher).hasRole("ADMIN")
                        .anyRequest().authenticated())
                .exceptionHandling(e -> e.accessDeniedHandler((new CustomAccessDeniedHandler())))
                .formLogin(auth -> auth.defaultSuccessUrl("/bidList/list", true))
                .logout(auth -> auth.logoutSuccessUrl("/home").invalidateHttpSession(true).deleteCookies("JSESSIONID"))
                .userDetailsService(customUserDetailsService)
                .sessionManagement(session -> session.maximumSessions(1)
                );


        return httpSecurity.build();
    }



}
