package com.UniqueIDGenerator.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserDTO {
    public String userName;
    public String emailId;
    public String phoneNumber;
}
