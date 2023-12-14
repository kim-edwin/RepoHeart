package com.example.heart.database.dao;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.heart.database.repository.HeartRepository;
import com.example.heart.database.repository.ResumeRepository;
import com.example.heart.database.repository.UserRepository;
import com.example.heart.model.entity.ResumeEntity;
import com.example.heart.model.entity.UserEntity;

@Service
public class MainDao {

    @Value("${file.upload.directory}")
    private String uploadDirectory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    // pdf를 이미지로 변환하여 저장하는 메소드
    private void convertPdfToImagesAndSave(MultipartFile pdfFile, Long resumeId) throws IOException {
        try (PDDocument document = PDDocument.load(pdfFile.getInputStream())) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            // 이미지를 저장할 폴더 생성
            Path resumeFolder = Paths.get(uploadDirectory, String.valueOf(resumeId));
            resumeFolder.toFile().mkdirs();

            for (int pageIndex = 0; pageIndex < document.getNumberOfPages(); pageIndex++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, 300, ImageType.RGB);

                // 이미지를 저장
                File outputImageFile = new File(resumeFolder.toFile(), resumeId + "_" + pageIndex + ".png");
                ImageIO.write(image, "png", outputImageFile);
            }
        }
    }

    // pdf업로드
    public void uploadPdf(MultipartFile pdfFile) throws IOException {
        // 로그인 기능이 없으므로 임시로 고정된 사용자명을 사용 (test001)
        String username = "test001";

        // 1. userRepository에 pdf를 올린 유저의 정보가 업로드되어야 함.
        UserEntity userEntity = userRepository.findByUserName(username)
                .orElseGet(() -> {
                    UserEntity newUser = new UserEntity();
                    newUser.setUserName(username);
                    return newUser;
                });

        // 2. resumeRepository에 pdf의 정보가 업로드되어야 함.
        ResumeEntity resumeEntity = new ResumeEntity();
        resumeEntity.setUserEntity(userEntity);
        resumeEntity.setResumeFilename(pdfFile.getOriginalFilename());

        resumeRepository.save(resumeEntity);

        // 3. PDF를 이미지로 변환하여 저장
        convertPdfToImagesAndSave(pdfFile, resumeEntity.getResumeId());
    }

    // 나머지 메소드들...

}
