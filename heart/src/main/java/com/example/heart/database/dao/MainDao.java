package com.example.heart.database.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.heart.database.repository.HeartRepository;
import com.example.heart.database.repository.ResumeRepository;
import com.example.heart.database.repository.UserRepository;

@Service
public class MainDao {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private HeartRepository heartRepository;

}
