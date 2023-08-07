package com.nnk.springboot.implementations;

import com.nnk.springboot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OnStartUp implements CommandLineRunner {

    private final UserService userService;
    @Override
    public void run(String... args) throws Exception {
        userService.creatDefaultAdminUser();
    }


}
