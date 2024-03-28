package com.registrationlogin.app.service;

import com.registrationlogin.app.dto.LoginDTO;
import com.registrationlogin.app.dto.UserDTO;
import com.registrationlogin.app.entity.User;

public interface UserService {
    User registerUser (UserDTO userDTO);
    User loginUser (LoginDTO loginDTO);
    User getUserByUsername (String username);
}
