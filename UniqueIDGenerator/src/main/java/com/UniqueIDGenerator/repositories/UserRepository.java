package com.UniqueIDGenerator.repositories;

import com.UniqueIDGenerator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
