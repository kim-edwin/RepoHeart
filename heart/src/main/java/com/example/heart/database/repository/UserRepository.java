package com.example.heart.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.heart.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String username);
}
