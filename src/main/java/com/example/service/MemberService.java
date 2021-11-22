package com.example.service;

import com.example.entity.Member;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    
    //회원가입
    public void insertUser(Member member);
}
