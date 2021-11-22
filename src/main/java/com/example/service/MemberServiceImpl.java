package com.example.service;

import javax.persistence.EntityManagerFactory;

import com.example.entity.Member;
import com.example.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    MemberRepository uRepository;

    //회원가입
    @Override
    public void insertUser(Member user) {
        uRepository.save(user);
    }
    
}
