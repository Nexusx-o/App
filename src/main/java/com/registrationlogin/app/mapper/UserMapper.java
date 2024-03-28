package com.registrationlogin.app.mapper;

import com.registrationlogin.app.dto.UserDTO;
import com.registrationlogin.app.entity.User;

public class UserMapper {
    public User toEntityCreate(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setUserRole(userDTO.getUserRole());

        return user;
    }

    public User toEntityUpdate(User user, UserDTO userDTO) {
        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setUserRole(userDTO.getUserRole());

        return user;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserRole(user.getUserRole());

        return userDTO;
    }
}
