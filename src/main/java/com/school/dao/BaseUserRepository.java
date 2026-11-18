package com.school.dao;

import com.school.entities.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseUserRepository extends JpaRepository<BaseUser, Integer> {
    Optional<BaseUser> findByUsername(String username);
}
