    package com.nnk.springboot.implementations;

    import com.nnk.springboot.controllers.LoginController;
    import com.nnk.springboot.domain.User;
    import com.nnk.springboot.repositories.UserRepository;
    import com.nnk.springboot.services.UserService;
    import lombok.AllArgsConstructor;
    import lombok.extern.log4j.Log4j2;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;
    import org.springframework.ui.Model;

    import java.security.Principal;
    import java.util.List;
    import java.util.NoSuchElementException;
    import java.util.Optional;

    @AllArgsConstructor(onConstructor = @__(@Autowired))
    @Log4j2
    @Service
    public class UserServiceImpl implements UserService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final LoginController loginController;

        public List<User> getAllUsers(){
            log.info("getAllUsers method called");
            return userRepository.findAll();
        }

        public User getUserByUsername(String username){
            log.info("getUserByUsername method called with: {}", username);
            Optional<User>userOptional = userRepository.findByUsername(username);
            return userOptional.orElseThrow(() -> new NoSuchElementException("Invalid user username:" + username));
        }

        public void saveUser(User user){
            log.info("saveUser method called with: {}", user);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }

        public void updateUser(User updatedUser, int userToUpdateId){
            log.info("updateUser method called with: {}, {}", updatedUser, userToUpdateId);
            User userToUpdate = getUserById(userToUpdateId);
            userToUpdate.setFullname(updatedUser.getFullname());
            userToUpdate.setUsername(updatedUser.getUsername());
            userToUpdate.setPassword(updatedUser.getPassword());
            userToUpdate.setRole(updatedUser.getRole());
            saveUser(userToUpdate);
        }

        public User getUserById(int id){
            log.info("getUserById method called with: {}", id);
            Optional<User> userOptional = userRepository.findById(id);
            return userOptional.orElseThrow(() -> new NoSuchElementException("Invalid user Id:" + id));
        }

        public void deleteUserById(int id){
            log.info("deleteUserById method called with: {}", id);
            userRepository.deleteById(id);
        }

        public void creatDefaultAdminUser() {
            Optional<User> userOptional = userRepository.findByUsername("defaultAdmin");
            if(userOptional.isEmpty()){
                User defaultAdminUser = new User();
                defaultAdminUser.setFullname("defaultAdminUser");
                defaultAdminUser.setUsername("defaultAdmin");
                defaultAdminUser.setPassword("Password1*");
                defaultAdminUser.setRole("ADMIN");
               saveUser(defaultAdminUser);
            }
        }
    }
