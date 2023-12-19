package com.example.heart.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.heart.database.repository.HeartRepository;
import com.example.heart.database.repository.ResumeRepository;
import com.example.heart.model.dto.HeartDto;
import com.example.heart.model.entity.HeartEntity;
import com.example.heart.model.entity.ResumeEntity;
import com.example.heart.model.java.HeartMapper;


@Service
public class HeartService {
    
    @Autowired
    private HeartRepository heartRepository;

    @Autowired
    private ResumeRepository resumeRepository;


    public void saveHeartLocation(HeartDto heartDto, Long resumeId) {
        Optional<ResumeEntity> optionalResumeEntity = resumeRepository.findById(resumeId);
        if (optionalResumeEntity.isPresent()) {
            ResumeEntity resumeEntity = optionalResumeEntity.get();
            HeartEntity heartEntity = HeartMapper.toEntity(heartDto, resumeEntity);
            heartRepository.save(heartEntity);
        } else {
            // ResumeEntity가 존재하지 않는 경우에 대한 처리
            // 예외 처리 또는 원하는 방식으로 처리
        }
    }

    public List<HeartDto> getHeartsByResumeId(Long resumeId) {
        // HeartRepository에서 resumeId에 해당하는 하트들을 조회하여 HeartDto 리스트로 변환 후 반환
        // (필요하다면 추가적인 로직을 구현)
        List<HeartEntity> heartEntities = heartRepository.findByResumeEntity_ResumeId(resumeId);
        return heartEntities.stream()
                .map(HeartMapper::toDto)
                .collect(Collectors.toList());
    }
}
