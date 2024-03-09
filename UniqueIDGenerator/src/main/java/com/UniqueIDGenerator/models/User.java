package com.UniqueIDGenerator.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AppUser")
public class User {
    @Id
    private long Id;
    private String username;
    private String emailId;
    private String phoneNumber;
}
