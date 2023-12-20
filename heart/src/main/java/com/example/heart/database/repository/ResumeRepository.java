package com.example.heart.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.heart.model.entity.ResumeEntity;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {
    Optional<ResumeEntity> findById(Long resumeId);

    
}
