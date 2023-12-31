package com.nnk.springboot.implementations;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class responsible for handling custom user-specific data retrieval during the authentication process.
 * This service integrates with Spring Security's {@link UserDetailsService} to fetch user details
 * based on the provided username. The actual user data is fetched from a {@link UserRepository}.
 *
 */
@Service
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomUserDetailsService implements UserDetailsService {

    /** The repository responsible for managing user data. */
    private final UserRepository userRepository;

    /**
     * This method overrides the loadUserByUsername() for Spring Security's {@link UserDetailsService} and retrieves
     * the user data from a {@link UserRepository}. With the user data, the user details is constructed then returned.
     *
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername method called with: {}", username);
        com.nnk.springboot.domain.User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: " + username));

        UserDetails userDetails;

            userDetails = User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();

        return userDetails;
    }

}
