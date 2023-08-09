package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;

import java.util.List;

/**
 * Service interface for operations related to User.
 */
public interface UserService {
    
    /**
     * Retrieves all User entities.
     *
     * @return a list of all users
     */
    List<User> getAllUsers();

    /**
     * Retrieves a User entity by its username.
     *
     * @param username the username of the user to retrieve
     * @return the user corresponding to the given username
     */
    User getUserByUsername(String username);

    /**
     * Persists a given User entity.
     *
     * @param user the user to save
     */
   void saveUser(User user);

    /**
     * Updates an existing User entity identified by its ID.
     *
     * @param updatedUser the updated user
     * @param userToUpdateId the ID of the user to update
     */
    void updateUser(User updatedUser, int userToUpdateId);

    /**
     * Retrieves a User entity by its ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user corresponding to the given ID
     */
   User getUserById(int id);

    /**
     * Deletes a User entity identified by its ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteUserById(int id);


    /**
     * Creates and saves a default admin user.
     * for initial setup and ensuring the presence of an admin account.
     */
    void creatDefaultAdminUser();
}
