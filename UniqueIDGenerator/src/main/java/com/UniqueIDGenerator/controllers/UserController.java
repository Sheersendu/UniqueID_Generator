package com.UniqueIDGenerator.controllers;

import com.UniqueIDGenerator.dtos.CreateUserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping(value = "/register", produces = "application/json")
    public void createUser(@RequestBody CreateUserDTO createUserDTO)
    {

    }
}
