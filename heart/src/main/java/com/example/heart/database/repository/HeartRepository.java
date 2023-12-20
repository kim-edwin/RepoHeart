package com.example.heart.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.heart.model.entity.HeartEntity;

@Repository
public interface HeartRepository extends JpaRepository<HeartEntity, Long> {
                                                                    //HeartEntity 기본키타입이 Long
    List<HeartEntity> findByResumeEntity_ResumeId(Long resumeId);
<<<<<<< HEAD
    
    void deleteByResumeEntity_ResumeId(Long resumeId);
=======
    //List임 여러개하트찍히니까
    // 특정 resumeId에 해당하는 ResumeEntity에 속한 
    // 모든 HeartEntity를 가져올 수 o
>>>>>>> 3df007e2e0a809fc60c42d5fc55da5070a01b8dc
}

