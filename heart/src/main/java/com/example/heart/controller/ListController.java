package com.example.heart.controller;

import com.example.heart.database.repository.ResumeRepository;
import com.example.heart.model.entity.ResumeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ListController {

    @Autowired
    private ResumeRepository resumeRepository;

    @GetMapping("/list")
    public String list(@RequestParam(name = "searchKeyword", required = false) String searchKeyword, Model model) {
        List<ResumeEntity> resumeList;

        // 검색어가 있을 경우
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            // 검색어를 사용하여 파일 이름에 해당 키워드가 포함된 데이터를 검색
            resumeList = resumeRepository.findByResumeFilenameContaining(searchKeyword.toLowerCase());
        } else {
            // 검색어가 없을 경우 모든 데이터를 가져옴
            resumeList = resumeRepository.findAll();
        }

        // Model에 데이터를 추가하여 View에 전달
        model.addAttribute("resumeList", resumeList);

        return "list";
    }
}