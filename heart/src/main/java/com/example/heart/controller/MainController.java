package com.example.heart.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        // 이미지 파일의 경로 목록을 가져와 모델에 추가
        List<String> imagePaths = getImagePathsForResume(resumeId);
        model.addAttribute("images", imagePaths);
    
        return "viewer";
    }

    private List<String> getImagePathsForResume(Long resumeId) {
        List<String> imagePaths = new ArrayList<>();
        String basePath = "/static/uploads/" + resumeId + "/";
    
        // 해당 경로의 폴더를 가져옴
        File folder = new File(getClass().getResource(basePath).getFile());
    
        // 폴더가 존재하고 디렉토리인 경우 파일 목록을 가져옴
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
    
            // 파일 개수를 페이지 수로 사용
            int pageCount = files.length;
    
            // 파일명을 resumeId_pageindex로 추가
            for (int i = 0; i < pageCount; i++) {
                imagePaths.add(basePath + resumeId + "_" + i + ".png");
            }
        }
    
        return imagePaths;
    }
}