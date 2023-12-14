package com.example.heart.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    @Value("${file.upload.directory}") // application.yml에 설정한 파일 업로드 디렉토리
    private String fileUploadDirectory;

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    
    @GetMapping("/viewer/{resumeId}")
    public String viewer(@PathVariable Long resumeId, Model model) {
        model.addAttribute("resumeId", resumeId);
        return "viewer";
    }
}
