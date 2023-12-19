package com.example.heart.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.heart.model.dto.HeartDto;
import com.example.heart.service.HeartService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HeartController {

    @Autowired
    private HeartService heartService;

    @PostConstruct
    public void init() {
        log.info("[HeartController] HeartService is {}", (heartService != null) ? "available" : "not available");
    }

    @PostMapping("/saveHeartLocations")
    public ResponseEntity<String> saveHeartLocations(@RequestBody Map<String, Object> requestData) {
        try {
            log.info("[HeartController][saveHeartLocations] Start");

            List<Map<String, Object>> heartDtoList = (List<Map<String, Object>>) requestData.get("heartDtoList");
            log.info("[HeartController][saveHeartLocations] HeartDtoList :" + heartDtoList);

            // HeartDto 리스트를 순회하면서 처리
            for (Map<String, Object> heartDtoMap : heartDtoList) {
                HeartDto heartDto = new HeartDto();
                Long resumeId = Long.parseLong((String) heartDtoMap.get("resumeId"));
                int pageNumber = (int) heartDtoMap.get("pageNumber");
                double xCoordinate = ((Number) heartDtoMap.get("x")).doubleValue();
                double yCoordinate = ((Number) heartDtoMap.get("y")).doubleValue();
    
                heartDto.setResumeId(resumeId);
                heartDto.setResumePageNumber(pageNumber);
                heartDto.setXCoordinate(xCoordinate);
                heartDto.setYCoordinate(yCoordinate);

                log.info("[HeartController][saveHeartLocations] Dto 생성 완료 " + heartDto);
    
                heartService.saveHeartLocations(heartDto, resumeId);
                log.info("[HeartController][saveHeartLocations] Service로 넘기기 완료");
            }

            return ResponseEntity.ok("Heart locations saved successfully.");
        } catch (Exception e) {
            // 예외 처리
            log.info("[HeartController][saveHeartLocations] 오류 발생" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving heart locations: " + e.getMessage());
        }
    }

}

