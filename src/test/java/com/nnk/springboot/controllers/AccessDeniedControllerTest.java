package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class AccessDeniedControllerTest {

    @InjectMocks
    AccessDeniedController accessDeniedController;
    @Mock
    UserService userService;

    @Mock
    Principal principal;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accessDeniedController).build();
    }

    @Test
    @WithMockUser(username = "testUser")
    void handleError() throws Exception {
        //Arrange
        when(principal.getName()).thenReturn("testUser");
        when(userService.getUserByUsername("testUser")).thenReturn(new User());

        //Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/access-denied").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("403"));

        verify(userService).getUserByUsername("testUser");
    }
}
