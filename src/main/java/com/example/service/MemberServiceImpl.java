package com.example.service;

import java.util.Optional;

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
    MemberRepository mRepository;

    //회원가입
    @Override
    public void insertUser(Member user) {
        mRepository.save(user);
    }

    //회원 정보 찾기
    @Override
    public Member selectUserOne(String id) {
        Optional<Member> member = mRepository.findById(id);
        return member.orElse(null);
    }

    //회원 정보 수정
    @Override
    public void updateUser(Member member) {
        mRepository.save(member);
    }

    // userid 중복 확인
    @Override
    public int checkUserid(String id) {
        return mRepository.queryCheckUserid(id);
    }
    
}
