package com.example.service;

import com.example.entity.Scout;

import org.springframework.stereotype.Service;

@Service
public interface ScoutService {
    
    // 스카우트 추가
    public void insertScout(Scout scout);
}
