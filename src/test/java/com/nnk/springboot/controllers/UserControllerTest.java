package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.implementations.UserServiceImpl;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;
    @Mock
    Principal principal;

    User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = new User();
        mockUser.setFullname("testUser");
        mockUser.setUsername("tester");
        mockUser.setPassword("testPassword");
        mockUser.setRole("user");
    }

    @Test
    void testHome() {
        //Arrange
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(mockUser));
        when(principal.getName()).thenReturn("tester");
        when(userService.getUserByUsername(anyString())).thenReturn(mockUser);

        //Act
        String result = userController.home(model, principal);

        //Assert
        verify(userService, times(1)).getAllUsers();
        verify(userService, times(1)).getUserByUsername(anyString());
        assertThat(result).isEqualTo("user/list");
    }

    @Test
    void testAddUserForm() {
        //Act
        String result = userController.addUserForm(mockUser, model);

        //Assert
        assertThat(result).isEqualTo("user/add");
    }

    @Test
    void testValidate() {
        //Arrange
        when(bindingResult.hasErrors()).thenReturn(false);

        //Act
        String result = userController.validate(mockUser, bindingResult, model);

        //Assert
        verify(userService, times(1)).saveUser(mockUser);
        assertThat(result).isEqualTo("redirect:/user/list");
    }

    @Test
    void testShowUpdateForm() {
        //Arrange
        when(userService.getUserById(anyInt())).thenReturn(mockUser);

        //Act
        String result = userController.showUpdateForm(1, model);

        //Assert
        verify(userService, times(1)).getUserById(anyInt());
        assertThat(result).isEqualTo("user/update");
    }

    @Test
    void testUpdateUser() {
        //Arrange
        when(bindingResult.hasErrors()).thenReturn(false);

        //Act
        String result = userController.updateUser(1, mockUser, bindingResult, model);

        //Assert
        verify(userService, times(1)).updateUser(mockUser, 1);
        assertThat(result).isEqualTo("redirect:/user/list");
    }

    @Test
    void testDeleteUser() {
        //Act
        String result = userController.deleteUser(1);

        //Assert
        verify(userService, times(1)).deleteUserById(1);
        assertThat(result).isEqualTo("redirect:/user/list");
    }
}
