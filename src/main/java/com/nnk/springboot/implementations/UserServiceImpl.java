    package com.nnk.springboot.implementations;

    import com.nnk.springboot.controllers.LoginController;
    import com.nnk.springboot.domain.User;
    import com.nnk.springboot.repositories.UserRepository;
    import com.nnk.springboot.services.UserService;
    import jakarta.validation.Valid;
    import lombok.AllArgsConstructor;
    import lombok.extern.log4j.Log4j2;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;
    import org.springframework.ui.Model;

    import java.security.Principal;
    import java.util.List;
    import java.util.NoSuchElementException;
    import java.util.Optional;

    /**
     * Implementation of the {@link UserService} interface.
     * Manages CRUD operations for the {@link User} domain.
     *
     */
    @AllArgsConstructor(onConstructor = @__(@Autowired))
    @Log4j2
    @Service
    public class UserServiceImpl implements UserService {

        /** Repository to perform CRUD operations for {@link User}. */
        private final UserRepository userRepository;

        /** Encoder for hashing passwords. */
        private final PasswordEncoder passwordEncoder;

        /**
         * Fetches all users from the database.
         *
         * @return a list of all user entities.
         */
        public List<User> getAllUsers(){
            log.info("getAllUsers method called");
            return userRepository.findAll();
        }

        /**
         * Retrieves a specific {@link User} using its username.
         *
         * @param username the username of the user to retrieve.
         * @return the corresponding user entity.
         * @throws NoSuchElementException if no user is found with the given username.
         */
        public User getUserByUsername(String username){
            log.info("getUserByUsername method called with: {}", username);
            Optional<User>userOptional = userRepository.findByUsername(username);
            return userOptional.orElseThrow(() -> new NoSuchElementException("Invalid user username:" + username));
        }

        /**
         * Saves a given {@link User} to the database, encoding its password.
         *
         * @param user the user entity to save.
         */
        public void saveUser(User user){
            log.info("saveUser method called with: {}", user);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }

        /**
         * Updates an existing user using the provided updated user details.
         *
         * @param updatedUser the updated user details.
         * @param userToUpdateId the ID of the user to update.
         */
        public void updateUser(User updatedUser, int userToUpdateId){
            log.info("updateUser method called with: {}, {}", updatedUser, userToUpdateId);
            User userToUpdate = getUserById(userToUpdateId);
            userToUpdate.setFullname(updatedUser.getFullname());
            userToUpdate.setUsername(updatedUser.getUsername());
            userToUpdate.setPassword(updatedUser.getPassword());
            userToUpdate.setRole(updatedUser.getRole());
            saveUser(userToUpdate);
        }

        /**
         * Retrieves a specific {@link User} using its ID.
         *
         * @param id the ID of the user to retrieve.
         * @return the corresponding user entity.
         * @throws NoSuchElementException if no user is found for the given ID.
         */
        public User getUserById(int id){
            log.info("getUserById method called with: {}", id);
            Optional<User> userOptional = userRepository.findById(id);
            return userOptional.orElseThrow(() -> new NoSuchElementException("Invalid user Id:" + id));
        }

        /**
         * Deletes a user from the database using its ID.
         *
         * @param id the ID of the user to delete.
         */
        public void deleteUserById(int id){
            log.info("deleteUserById method called with: {}", id);
            userRepository.deleteById(id);
        }

        /**
         * Creates a default admin user if it doesn't already exist.
         */
        public void creatDefaultAdminUser() {
            Optional<User> userOptional = userRepository.findByUsername("defaultAdmin");
            if(userOptional.isEmpty()){
                User defaultAdminUser = new User();
                defaultAdminUser.setFullname("defaultAdminUser");
                defaultAdminUser.setUsername("defaultAdmin");
                defaultAdminUser.setPassword(System.getenv("PCS_DEFAULT_ADMIN_PASSWORD"));

                defaultAdminUser.setRole("ADMIN");
                saveUser(defaultAdminUser);
            }
        }
    }
