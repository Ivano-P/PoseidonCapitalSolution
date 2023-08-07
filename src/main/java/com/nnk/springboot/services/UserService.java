package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserByUsername(String username);

   void saveUser(User user);

    void updateUser(User updatedUser, int userToUpdateId);

   User getUserById(int id);

    void deleteUserById(int id);

    void creatDefaultAdminUser();
}
