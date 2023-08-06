package com.nnk.springboot.implementations;

import com.nnk.springboot.security.WebSecurityConfig;
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
@Configuration
@EnableWebSecurity
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WebSecurityConfigImpl implements WebSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        log.info("securityFilterChain method called with: {}", httpSecurity );


        //set pages that are accessible without being logged in
        httpSecurity.authorizeHttpRequests(auth -> auth.requestMatchers("/user/list", "/user/add", "/user/update")
                .permitAll()
                .anyRequest()
                .authenticated());


        //set my customized login page for spring security and makes it accessible without being logged-in
        httpSecurity.formLogin(auth -> auth.defaultSuccessUrl("/home", true));


         //to make admin pages accessible only to admin users.
         httpSecurity.authorizeHttpRequests(auth -> auth
            .requestMatchers("/bidList/list").hasRole("ADMIN"));



        httpSecurity.logout(auth -> auth.logoutSuccessUrl("/").invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));


        //to check user in db for login
        httpSecurity.userDetailsService(customUserDetailsService);

        return httpSecurity.build();
    }
}
