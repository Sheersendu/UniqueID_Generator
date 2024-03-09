package com.UniqueIDGenerator.controllers;

import com.UniqueIDGenerator.dtos.CreateUserDTO;
import com.UniqueIDGenerator.models.User;
import com.UniqueIDGenerator.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", produces = "application/json")
    public User createUser(@RequestBody CreateUserDTO createUserDTO)
    {
        return userService.createUser(createUserDTO);
    }
}
