package com.example.heart.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.heart.model.entity.HeartEntity;

@Repository
public interface HeartRepository extends JpaRepository<HeartEntity, Long> {
                                                                    //HeartEntity 기본키타입이 Long
    List<HeartEntity> findByResumeEntity_ResumeId(Long resumeId);

    void deleteByResumeEntity_ResumeId(Long resumeId);

}

