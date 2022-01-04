package com.example.service;

import com.example.entity.Review;
import com.example.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService{
    
    @Autowired
    ReviewRepository rRepository;

    // 리뷰 등록
    @Override
    public void insertReview(Review review) {
        rRepository.save(review);
    }

    
}
