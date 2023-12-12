package com.example.heart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.heart.database.dao.MainDao;

@Service
public class MainService {
    
    @Autowired
    private MainDao mainDao;
}
