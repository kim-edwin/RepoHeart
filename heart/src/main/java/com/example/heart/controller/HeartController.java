package com.example.heart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/updateHearts")
    public ResponseEntity<String> updateHearts(@RequestBody List<HeartDto> heartDtoList) {
        try {
            log.info("[HeartController][updateHearts] Start");

            // HeartDtoList에서 resumeID 추출
            if (!heartDtoList.isEmpty()) {
                Long resumeId = heartDtoList.get(0).getResumeId();

                // 서비스를 통해 삭제
                heartService.deleteHeartsByResumeId(resumeId);
                log.info("[HeartController][updateHearts] Deleted all hearts for resumeId: " + resumeId);

                // HeartDtoList를 반복문으로 돌면서 서비스의 updateHearts 실행
                for (HeartDto heartDto : heartDtoList) {
                    heartService.updateHearts(heartDto, resumeId);
                }

                // 성공적으로 처리되면 OK 응답 반환
                return ResponseEntity.ok("Hearts updated successfully");
            } else {
                // HeartDtoList가 비어있으면 Bad Request 응답 반환
                return ResponseEntity.badRequest().body("HeartDtoList is empty");
            }

        } catch (Exception e) {
            // 오류 발생 시 Bad Request 응답 반환
            log.error("[HeartController][updateHearts] Error: " + e.getMessage(), e);
            return ResponseEntity.badRequest().body("Error updating hearts: " + e.getMessage());
        }
    }


    @GetMapping("/getHeartsByResumeId/{resumeId}")
    public List<HeartDto> getHeartsByResumeId(@PathVariable Long resumeId) {
        return heartService.getHeartsByResumeId(resumeId);
    }

}

