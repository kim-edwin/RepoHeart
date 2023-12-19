package com.example.heart.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.heart.model.dto.UserDto;

public interface LoginRepository extends JpaRepository<UserDto, String> {
    
    @Query(value = "select * from user where name = :name", nativeQuery = true)
    public UserDto getUserDtoByName(@Param(value = "name") String name);

}