package com.nnk.springboot.implementations;

import com.nnk.springboot.config.WebSecurityConfig;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Configuration
@EnableWebSecurity
public class WebSecurityConfigImpl implements WebSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    private final HandlerMappingIntrospector handlerMappingIntrospector;

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
                .formLogin(auth -> auth.defaultSuccessUrl("/bidList/list", true))
                .logout(auth -> auth.logoutSuccessUrl("/home").invalidateHttpSession(true).deleteCookies("JSESSIONID"))
                .userDetailsService(customUserDetailsService)
                .sessionManagement(session -> session.maximumSessions(1)
                );

        return httpSecurity.build();
    }
}
