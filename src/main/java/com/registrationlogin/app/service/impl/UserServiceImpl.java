package com.registrationlogin.app.service.impl;

import com.registrationlogin.app.dto.LoginDTO;
import com.registrationlogin.app.dto.UserDTO;
import com.registrationlogin.app.entity.User;
import com.registrationlogin.app.mapper.UserMapper;
import com.registrationlogin.app.repository.UserRepository;
import com.registrationlogin.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    protected UserRepository userRepository;
    protected UserMapper userMapper;

    /**
     * Register new user
     *
     * @param userDTO, new user details
     * @return User if the userDTO is valid
     * @author anujam
     * @Date 3/28/2024
     */
    @Override
    public User registerUser(UserDTO userDTO) {
        //validate input data
        if (userDTO.getUsername() == null || userDTO.getPassword() == null || userDTO.getUserRole() == null ||
        userDTO.getUsername().isBlank() || userDTO.getPassword().isBlank() || userDTO.getUserRole().isBlank()) {
            throw new IllegalArgumentException("Username, password and user role are required!");
        }
        //check if username already exist
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists!");
        }
        //save and return new user
        User user;

        if (userDTO.getUserId() == null) {
            user = userRepository.save(userMapper.toEntityCreate(userDTO));
            log.info("User Created Successfully!");
        } else {
            User user1 = userRepository.getById(userDTO.getUserId());
            user = userRepository.save(userMapper.toEntityUpdate(user1, userDTO));
            log.info("User Updated Successfully!");
        }
        return user;
    }



    /**
     * User login
     *
     * @param LoginDTO, login details
     * @return User if the LoginDTO is valid
     * @author anujam
     * @Date 3/28/2024
     */
    @Override
    public User loginUser(LoginDTO loginDTO) {
        //validate input data
        if (loginDTO.getUsername() == null || loginDTO.getPassword() == null ||
            loginDTO.getUsername().isBlank() || loginDTO.getPassword().isBlank()) {
            throw new IllegalArgumentException("Username and password are required!");
        }
        //check if the user exist and if exist verify password
        User user = userRepository.findByUsername(loginDTO.getUsername());

        if (user != null && loginDTO.getPassword().equals(user.getPassword())) {
            log.info("Access Granted for: " + user.getUsername());
            return user;
        } else {
            throw new IllegalArgumentException("Invalid username or password!");
        }
    }

    /**
     * Get User by username
     *
     * @param username
     * @return User if the username is correct
     * @author anujam
     * @Date 3/28/2024
     */
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Delete user by userId
     *
     * @param userId
     * @return void
     * @author anujam
     * @Date 3/28/2024
     */
    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Get All users
     *
     * @param ()
     * @return List of Users
     * @author anujam
     * @Date 3/28/2024
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
