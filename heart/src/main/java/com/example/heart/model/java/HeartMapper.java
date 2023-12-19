package com.example.heart.model.java;

import com.example.heart.model.dto.HeartDto;
import com.example.heart.model.entity.HeartEntity;
import com.example.heart.model.entity.ResumeEntity;

public class HeartMapper {
    
    public static HeartEntity toEntity(HeartDto heartDto, ResumeEntity resumeEntity) {
        HeartEntity heartEntity = new HeartEntity();
        heartEntity.setHeartId(heartDto.getHeartId());
        heartEntity.setResumeEntity(resumeEntity);
        heartEntity.setPageNumber(heartDto.getPageNumber());
        heartEntity.setXCoordinate(heartDto.getXCoordinate());
        heartEntity.setYCoordinate(heartDto.getYCoordinate());
        return heartEntity;
    }

    public static HeartDto toDto(HeartEntity heartEntity) {
        HeartDto heartDto = new HeartDto();
        heartDto.setHeartId(heartEntity.getHeartId());
        heartDto.setResumeId(heartEntity.getResumeEntity().getResumeId());
        heartDto.setPageNumber(heartEntity.getPageNumber());
        heartDto.setXCoordinate(heartEntity.getXCoordinate());
        heartDto.setYCoordinate(heartEntity.getYCoordinate());
        return heartDto;
    }
    
}
