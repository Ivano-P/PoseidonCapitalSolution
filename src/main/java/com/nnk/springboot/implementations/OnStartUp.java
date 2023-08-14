package com.nnk.springboot.implementations;

import com.nnk.springboot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Executes the specified logic on application startup.
 * This class initializes the default admin user by leveraging the {@link UserService}.
 *
 */
@Component
@AllArgsConstructor
public class OnStartUp implements CommandLineRunner {

    /** Service to manage user-related operations. */
    private final UserService userService;

    /**
     * This method runs on application startup. It initializes the default admin user.
     *
     * @param args arguments to the command line runner, not used in this implementation.
     * @throws Exception if there's an error during user creation.
     */
    @Override
    public void run(String... args) throws Exception {
        userService.creatDefaultAdminUser();
    }
}
