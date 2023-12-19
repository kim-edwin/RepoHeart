package com.example.heart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.heart.database.repository.ResumeRepository;
import com.example.heart.model.dto.ResumeDto;
import com.example.heart.model.entity.ResumeEntity;

@Service
public class ResumeService {
    
    @Autowired
    private ResumeRepository resumeRepository;

    public List<ResumeDto> getAllResumes() {
        // 모든 이력서 정보를 가져오는 메소드
        List<ResumeEntity> resumeEntities = resumeRepository.findAll();

        // ResumeEntity를 ResumeDto로 변환
        List<ResumeDto> resumeDtos = resumeEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return resumeDtos;
    }

    private ResumeDto convertToDto(ResumeEntity resumeEntity) {
        ResumeDto resumeDto = new ResumeDto();
        resumeDto.setResumeId(resumeEntity.getResumeId());
        resumeDto.setResumeFilename(resumeEntity.getResumeFilename());
        resumeDto.setUploadDate(resumeEntity.getUploadDate());
        resumeDto.setUserId(resumeEntity.getUserEntity().getUserId());

        // 다른 필요한 변환 로직이 있다면 추가

        return resumeDto;
    }
}
