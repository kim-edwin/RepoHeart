package com.example.heart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.heart.database.repository.HeartRepository;
import com.example.heart.database.repository.ResumeRepository;
import com.example.heart.model.dto.HeartDto;
import com.example.heart.model.entity.HeartEntity;
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

    public void deleteHeartsByResumeId(Long resumeId) {
        try {
            log.info("[HeartService][deleteHeartsByResumeId] Start");

            // 데이터베이스에서 해당 resumeID를 가지고 있는 모든 row 삭제
            heartRepository.deleteByResumeEntity_ResumeId(resumeId);

            log.info("[HeartService][deleteHeartsByResumeId] Deleted all hearts for resumeId: " + resumeId);

        } catch (Exception e) {
            // 오류 발생 시 예외 처리
            log.error("[HeartService][deleteHeartsByResumeId] Error: " + e.getMessage(), e);
            throw new RuntimeException("Error deleting hearts: " + e.getMessage());
        }
    }

    public void updateHearts(HeartDto heartDto, Long resumeId) {
        log.info("[HeartService][updateHearts] 실행 시작");
    
        HeartEntity newHeart = new HeartEntity();
        newHeart.setResumeEntity(resumeRepository.findById(resumeId).orElse(null));
        newHeart.setPageNumber(heartDto.getPageNumber());
        newHeart.setXCoordinate(heartDto.getXCoordinate());
        newHeart.setYCoordinate(heartDto.getYCoordinate());
        heartRepository.save(newHeart);
        

        log.info("[HeartService][updateHearts] Hearts updated successfully.");
    }
    

    public List<HeartDto> getHeartsByResumeId(Long resumeId) {
        log.info("[HeartService][getHeartsByResumeId]");
        
        List<HeartEntity> heartEntities = heartRepository.findByResumeEntity_ResumeId(resumeId);
        return heartEntities.stream().map(HeartMapper::toDto).collect(Collectors.toList());
    }
}


