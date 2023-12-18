package com.example.heart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.heart.model.dto.HeartDto;
import com.example.heart.service.HeartService;

@RestController
public class HeartController {

    @Autowired
    private HeartService heartService;

    @PostMapping("/saveHeartLocation")
    public void saveHeartLocation(@RequestBody HeartDto heartDto, @RequestParam Long resumeId) {
        heartService.saveHeartLocation(heartDto, resumeId);
    }

    @GetMapping("/getHeartsByResumeId")
    public List<HeartDto> getHeartsByResumeId(@RequestParam Long resumeId) {
        return heartService.getHeartsByResumeId(resumeId);
    }
}
