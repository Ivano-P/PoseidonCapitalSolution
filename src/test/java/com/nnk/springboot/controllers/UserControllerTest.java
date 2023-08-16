package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.implementations.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.security.Principal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;

    @Mock
    Model model;
    @Mock
    Principal principal;

    User mockUser;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockUser = new User();
        mockUser.setFullname("testUser");
        mockUser.setUsername("mockUsername");
        mockUser.setPassword("testPassword1*");
        mockUser.setRole("admin");
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "ADMIN")
    void testHome() throws Exception {
        //Arrange
        when(principal.getName()).thenReturn("mockUsername");
        when(userService.getUserByUsername(anyString())).thenReturn(mockUser);

        // Act  & Assert
        mockMvc.perform(get("/user/list").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));

        verify(userService, times(1)).getAllUsers();
        verify(userService, times(1)).getUserByUsername(anyString());
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "ADMIN")
    void testAddUserForm() throws Exception {
        //Arrange
        String result = userController.addUserForm(mockUser, model);

        // Assert & Act
        mockMvc.perform(get("/user/add")).andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "ADMIN")
    void testValidate() throws Exception {
        // Assert & act
        mockMvc.perform(post("/user/validate")
                        .flashAttr("user", mockUser))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/user/list"));
        verify(userService, times(1)).saveUser(mockUser);
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "ADMIN")
    void testShowUpdateForm() throws Exception {
        //Arrange
        int id = 1;
        when(userService.getUserById(anyInt())).thenReturn(mockUser);

        // Act & Assert
        mockMvc.perform(get("/user/update/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"));
        verify(userService, times(1)).getUserById(anyInt());
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "ADMIN")
    void testUpdateUser() throws Exception {
        //Arrange
        int id = 1;

        // Act & Assert
        mockMvc.perform(post("/user/update/" + id)
                        .flashAttr("user", mockUser))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/user/list"));
        verify(userService, times(1)).updateUser(mockUser, 1);
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "ADMIN")
    void testDeleteUser() throws Exception {
        //Arrange
        int id = 1;

        //Act & Assert
        mockMvc.perform(get("/user/delete/" + id))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/user/list"));
        verify(userService, times(1)).deleteUserById(1);
    }
}
