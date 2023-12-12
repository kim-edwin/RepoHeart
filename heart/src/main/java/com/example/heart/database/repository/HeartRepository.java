package com.example.heart.database.repository;

import com.example.heart.model.entity.HeartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartRepository extends JpaRepository<HeartEntity, Long> {
    
}

