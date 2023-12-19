package com.example.heart.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.heart.model.dto.UserDto;
import com.example.heart.database.repository.LoginRepository;

@Service
public class AuthUserService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        UserDto userDto = loginRepository.getUserDtoByName(name);

        // username의 데이터가 database에 존재함(가입함)!!
        if(userDto != null) {
            return new AuthUserDto(userDto);
        }
        

        // 존재하지 않음
        return null;
    }
    
}