package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

   void updateUser(User user);

   User getUserById(int id);

    void deleteUser(User user);

}
