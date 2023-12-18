package com.example.heart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.heart.model.dto.UserDto;
import com.example.heart.model.entity.UserEntity;
import com.example.heart.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
    
    @Autowired
    private UserService userService;

    // @GetMapping("/index1")
    // public String index1() {
    //     log.info("[UserController][index1] Start");
    //     return "index1";
    // }

    @GetMapping("/logout")
    public String logout() {
        log.info("[UserController][logout] Start");
        return "redirect:/loginPage";
    }

    @GetMapping("/join")
    public String joinPage() {
        log.info("[UserController][joinPage] Start");
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute UserDto dto) {

        // UserDto를 UserEntity로 변환
        UserEntity userEntity = convertToUserEntity(dto);

        // UserService에 UserEntity 전달
        userService.joinUserEntity(userEntity);

        return "redirect:/loginPage";
    }

    // UserDto를 UserEntity로 변환하는 메서드
    private UserEntity convertToUserEntity(UserDto dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(dto.getUserName());
        userEntity.setUserEmail(dto.getUserEmail());
        userEntity.setUserPassword(dto.getUserPassword());
        // 다른 필드들도 필요한 대로 설정

        return userEntity;
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        log.info("[UserController][loginPage] Start");
        return "login";
    }

    @GetMapping("/user")
    public String user() {
        log.info("[UserController][user] Start");
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        log.info("[UserController][admin] Start");
        return "admin";
    }
}

