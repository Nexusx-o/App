package com.registrationlogin.app.controller;

import com.registrationlogin.app.dto.LoginDTO;
import com.registrationlogin.app.dto.UserDTO;
import com.registrationlogin.app.entity.User;
import com.registrationlogin.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")

public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser (@RequestBody UserDTO userDTO) {
        try {
            return new ResponseEntity<>(userService.registerUser(userDTO), HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser (@RequestBody LoginDTO loginDTO) {
        try {
            return new ResponseEntity<>(userService.loginUser(loginDTO), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> findUser (@PathVariable String username) {
        try {
            User user = userService.getUserByUsername(username);

            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
