package com.example.heart.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.heart.service.MainService;

@Controller
@RequestMapping("/pdf")
public class FileUploadController {

    @Autowired
    private MainService mainService;

    @PostMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) { //postmapping
        try {
            // PDF를 업로드하고, 저장된 파일의 경로를 반환
            mainService.uploadPdf(file);
            return "upload_finish";
        } catch (IOException e) {
            return "Error uploading file: " + e.getMessage();
        }
    }
}