package com.example.heart.database.dao;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.heart.database.repository.HeartRepository;
import com.example.heart.database.repository.ResumeRepository;
import com.example.heart.database.repository.UserRepository;
import com.example.heart.model.entity.ResumeEntity;
import com.example.heart.model.entity.UserEntity;

@Service
public class MainDao {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private HeartRepository heartRepository;

/*** 
    //pdf 업로드
    public void uploadPdf(MultipartFile pdfFile) throws IOException {
        // 로그인 기능이 없으므로 임시로 고정된 사용자명을 사용 (test001)
        String username = "test001";

        // 1. userRepository에 pdf를 올린 유저의 정보가 업로드되어야 함.
        UserEntity userEntity = userRepository.findByUserName(username) //리포지토리에서 기존 유저 정보를 찾되,
        .orElseGet(() -> {
            UserEntity newUser = new UserEntity(); //기존 리포지토리에 유저 정보가 없다면 새로 생성하고 받아온 유저 이름으로 엔터티 생성
            newUser.setUser_name(username);
            return newUser;
        });
        
        // 2. resumeRepository에 pdf의 정보가 업로드되어야 함.
        ResumeEntity resumeEntity = new ResumeEntity(); //빈 Entity 생성
        resumeEntity.setUser_id(userEntity.getUser_id()); //빈 Entity에 위에서 확인한 userid를 넣음
        resumeEntity.setResume_filename(pdfFile.getOriginalFilename()); //pdfFile을 이름을 받아 넣음.

        resumeRepository.save(resumeEntity); //생성한 Entity를 Repository에 저장.

        // 3. 별도의 경로에 pdf 파일 원본이 저장되어야 함.
        savePdfFile(pdfFile, resumeEntity.getResume_id());
        //아래 메소드가 실행됨.
    }

    private void savePdfFile(MultipartFile pdfFile, Long resumeId) throws IOException {
        // 경로 객체를 만듦. "upload"라는 이름으로 설정.
        Path uploadPath = Paths.get("uploads");

        // 디렉토리가 없으면 생성
        if (!uploadPath.toFile().exists()) {
            uploadPath.toFile().mkdir();
        }

        // 파일명을 resumeId로 지정 (예시: "uploads/1.pdf")
        Path filePath = uploadPath.resolve(resumeId + ".pdf");

        // 파일을 저장
        pdfFile.transferTo(filePath.toFile());
    }
*/
}
