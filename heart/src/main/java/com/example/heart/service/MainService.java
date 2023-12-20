package com.example.heart.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.heart.database.dao.MainDao;
import com.example.heart.exception.InvalidFileException;

@Service
public class MainService {
    
    @Autowired
    private MainDao mainDao;

    //pdf 업로드
    public void uploadPdf(MultipartFile pdfFile) throws IOException {
        // 파일 확장자가 PDF가 아닌 경우 예외 처리
        if (!pdfFile.getOriginalFilename().toLowerCase().endsWith(".pdf")) { //파일의 원본이름을 읽어서 .pdf로 안끝나면 에러 메시지를 발생시킴
            throw new InvalidFileException("올바른 PDF 파일이 아닙니다.");
        }
        mainDao.uploadPdf(pdfFile);
    }
}
