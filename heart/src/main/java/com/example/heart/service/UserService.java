package com.example.heart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.heart.database.repository.LoginRepository;
import com.example.heart.model.dto.UserDto;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private LoginRepository loginRepository;

    // 로그인 성공시 >> 로그인 유무 저장
    public void updateIsLoginByName(String name, Boolean isLogin) {
        UserDto dto = loginRepository.getUserDtoByName(name);
        dto.setIsLogin(isLogin);
        loginRepository.save(dto);
    }

    public void joinUserDto(UserDto dto) {
        
        // 권한 적용 
        dto.setRole("USER");
        if(dto.getName().equals("admin")) {
            dto.setRole("ADMIN");
        } else if(dto.getName().equals("manager")) {
            dto.setRole("MANAGER");
        }

        // 비밀번호 암호화 적용
        String rawPwd = dto.getPwd();
        String encodedPwd = bCryptPasswordEncoder.encode(rawPwd);
        dto.setPwd(encodedPwd);

        dto.setIsLogin(false);

        // 신규 유저 database에 저장!!
        loginRepository.save(dto);
    }

}