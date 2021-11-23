package com.example.service;

import com.example.entity.Member;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    
    //회원가입
    public void insertUser(Member member);

    //회원 정보 찾기
    public Member selectUserOne(String id);

    //회원 정보 수정
    public void updateUser(Member member);
}
