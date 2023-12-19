package com.example.heart.database.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

//import com.example.heart.model.dto.UserDto;
import com.example.heart.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByName(String name);

    //@Query(value = "select * from user where name = :name", nativeQuery = true)
    //UserDto getUserDtoByName(@Param(value = "name") String name);

}
