package com.example.heart.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

    @Value("${file.upload.directory}") // application.yml에 설정한 파일 업로드 디렉토리
    private String fileUploadDirectory;

    // @GetMapping("/index")
    // public String index() {
    //     return "index";
    // }

    @GetMapping("/viewer/{resumeId}")
    public String viewer(@PathVariable Long resumeId, Model model) {
        // 이미지 파일의 경로 목록을 가져와 모델에 추가
        List<Map<String, Object>> imagePaths = getImagePathsForResume(resumeId);

        model.addAttribute("images", imagePaths);

        return "viewer";
    }

    @PostMapping("/list")
    public String list(Model model) {

        return "list"; // This should match the name of your list.html template
    }

    // 이미지 목록 가져오기 메서드
    private List<Map<String, Object>> getImagePathsForResume(Long resumeId) {
        List<Map<String, Object>> imagePaths = new ArrayList<>();
        String basePath = "/static/uploads/" + resumeId + "/";
        log.info("[MainController][getImagePathsForResume] basePath : "+basePath);

        // 해당 경로의 폴더를 가져옴
        Resource resource = new ClassPathResource(basePath);
        try {
            File folder = resource.getFile();
            log.info("[MainController][getImagePathsForResume] folder : " + folder);
            // 폴더가 존재하고 디렉토리인 경우 파일 목록을 가져옴
            if (folder.exists() && folder.isDirectory()) {
                File[] files = folder.listFiles();

                // 파일 개수를 페이지 수로 사용
                int pageCount = files.length;

                // 파일명을 resumeId_pageindex로 추가
                for (int i = 0; i < pageCount; i++) {
                    Map<String, Object> imageInfo = new HashMap<>();
                    imageInfo.put("path", basePath + resumeId + "_" + i + ".png");
                    imageInfo.put("pageIndex", i + 1);
                    imagePaths.add(imageInfo);
                }
            }
        } catch (IOException e) {
            // IOException 처리 로직 추가
            e.printStackTrace();
        }

        

        return imagePaths;
}

    
}
