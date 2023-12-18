package com.example.heart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.heart.database.repository.UserRepository;
import com.example.heart.model.entity.UserEntity;


@Service
public class UserService {
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void joinUserEntity(UserEntity userEntity) {
        // 권한 적용
        userEntity.setRole("USER");
        if ("root".equals(userEntity.getUserName())) {
            userEntity.setRole("ADMIN");
        }

        // 비밀번호 암호화 적용
        String rawPwd = userEntity.getPassword();
        String encodedPwd = bCryptPasswordEncoder.encode(rawPwd);
        userEntity.setPassword(encodedPwd);

        userRepository.save(userEntity);
    }
}
