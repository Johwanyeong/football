package com.example.service;

import com.example.entity.Review;

import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    
    // 리뷰 등록
    public void insertReview(Review review);
}
