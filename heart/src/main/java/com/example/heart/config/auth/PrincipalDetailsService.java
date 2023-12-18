package com.example.heart.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.heart.database.repository.UserRepository;
import com.example.heart.model.entity.UserEntity; // 변경된 import

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PrincipalDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("[PrincipalDetailsService][loadUserByUsername] Start");
        log.info("userId: " + userId);

        UserEntity user = userRepository.getUserEntityByUserId(userId); // 변경된 메서드 호출
        if (user != null) {
            // 이미 가입된 사용자!!!
            log.info(user.toString());
            return new PrincipalDetails(user); // 변경된 생성자 호출
        }

        // 가입되지 않은 사용자!!
        throw new UsernameNotFoundException(userId);
    }
}
