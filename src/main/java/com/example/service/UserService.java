package com.example.service;

import com.example.entity.User;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    
    //회원가입
    public void insertUser(User user);
}
