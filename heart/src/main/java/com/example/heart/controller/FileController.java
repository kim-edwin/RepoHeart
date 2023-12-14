package com.example.heart.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileController {

    private final String uploadDirectory = "uploads"; // 설정 파일에 지정한 업로드 디렉토리

    @GetMapping("/download/{resumeId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long resumeId) throws IOException {
        // 업로드된 파일의 경로를 가져옴
        Path filePath = Paths.get(uploadDirectory).resolve(resumeId + ".pdf");
        Resource resource = new FileSystemResource(filePath);

        // Content-Disposition 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + resource.getFilename());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
