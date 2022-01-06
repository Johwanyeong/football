package com.example.service;

import com.example.entity.Scout;
import com.example.repository.ScoutRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoutServiceImpl implements ScoutService{
    
    @Autowired
    ScoutRepository sRepository;

    // 스카우트 추가
    @Override
    public void insertScout(Scout scout) {
        sRepository.save(scout);
    }
}
