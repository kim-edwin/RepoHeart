package com.example.heart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.heart.database.repository.HeartRepository;
import com.example.heart.database.repository.ResumeRepository;
import com.example.heart.model.dto.HeartDto;
import com.example.heart.model.entity.HeartEntity;
import com.example.heart.model.entity.ResumeEntity;
import com.example.heart.model.java.HeartMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class HeartService {

    @Autowired
    private HeartRepository heartRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    public void saveHeartLocations(HeartDto heartDto, Long resumeId) {
        log.info("[HeartService][saveHeartLocations]");

        // ResumeEntity 조회
        Optional<ResumeEntity> optionalResumeEntity = resumeRepository.findById(resumeId);

        // ResumeEntity가 존재하면 HeartDto를 HeartEntity로 변환하여 저장
        if (optionalResumeEntity.isPresent()) {
            ResumeEntity resumeEntity = optionalResumeEntity.get();

            // HeartDto를 HeartEntity로 변환
            HeartEntity heartEntity = HeartMapper.toEntity(heartDto, resumeEntity);

            // HeartEntity 저장
            heartRepository.save(heartEntity);
            log.info("[HeartService][saveHeartLocations] HeartEntity saved successfully.");
        } else {
            log.error("[HeartService][saveHeartLocations] ResumeEntity not found for resumeId: {}", resumeId);
            // ResumeEntity가 존재하지 않는 경우에 대한 처리
            // 예외 처리 또는 원하는 방식으로 처리
        }
    }
}
