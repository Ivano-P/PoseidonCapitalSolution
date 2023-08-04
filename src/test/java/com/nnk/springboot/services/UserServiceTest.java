package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.implementations.UserServiceImpl;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    public UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

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
    void testGetAllUsers() {
        // Arrange
        when(userRepository.findAll()).thenReturn(Collections.singletonList(mockUser));

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        verify(userRepository, times(1)).findAll();
        assertThat(result).containsExactly(mockUser);
    }

    @Test
    void testGetUserByUsername() {
        // Arrange
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockUser));

        // Act
        User result = userService.getUserByUsername("tester");

        // Assert
        verify(userRepository, times(1)).findByUsername(anyString());
        assertThat(result).isEqualTo(mockUser);
    }

    @Test
    void testSaveUser() {
        // Arrange
        when(passwordEncoder.encode(anyString())).thenReturn("encryptedPassword");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // Act
        userService.saveUser(mockUser);

        // Assert
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser() {
        // Arrange
        when(passwordEncoder.encode(anyString())).thenReturn("encryptedPassword");
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(mockUser));

        // Act
        userService.updateUser(mockUser, 1);

        // Assert
        verify(userRepository, times(1)).findById(anyInt());
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserById() {
        // Arrange
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(mockUser));

        // Act
        User result = userService.getUserById(1);

        // Assert
        verify(userRepository, times(1)).findById(anyInt());
        assertThat(result).isEqualTo(mockUser);
    }

    @Test
    void testDeleteUserById() {
        // Act
        userService.deleteUserById(1);

        // Assert
        verify(userRepository, times(1)).deleteById(1);
    }


}
