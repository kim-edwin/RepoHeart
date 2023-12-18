package com.example.heart.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.heart.model.entity.HeartEntity;

@Repository
public interface HeartRepository extends JpaRepository<HeartEntity, Long> {
    
    List<HeartEntity> findByResumeEntity_ResumeId(Long resumeId);
    
}

