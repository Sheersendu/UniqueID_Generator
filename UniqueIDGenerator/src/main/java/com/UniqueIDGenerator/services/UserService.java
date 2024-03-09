package com.UniqueIDGenerator.services;

import com.UniqueIDGenerator.dtos.CreateUserDTO;
import com.UniqueIDGenerator.models.User;
import com.UniqueIDGenerator.repositories.UserRepository;
import com.UniqueIDGenerator.utilities.SnowflakeIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SnowflakeIDGenerator snowflakeIDGenerator;

    @Autowired
    public UserService(UserRepository userRepository, SnowflakeIDGenerator snowflakeIDGenerator) {
        this.userRepository = userRepository;
        this.snowflakeIDGenerator = snowflakeIDGenerator;
    }

    public User createUser(CreateUserDTO user) {
        long userId = snowflakeIDGenerator.nextId();
        User newUser = User.builder()
                .Id(userId)
                .username(user.getUserName())
                .emailId(user.getEmailId())
                .phoneNumber(user.phoneNumber)
                .build();
        userRepository.save(newUser);
        return newUser;
    }
}
