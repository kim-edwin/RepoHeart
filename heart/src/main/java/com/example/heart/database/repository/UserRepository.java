package com.example.heart.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//import com.example.heart.model.entity.UserEntity;
import com.example.heart.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String username);

    @Query(value ="SELECT u.* FROM heartDB.user u WHERE u.username = :username", nativeQuery= true)
    UserEntity getUserEntityByUserId(@Param(value = "username") String username);
}
