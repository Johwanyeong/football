package com.example.service;

import java.util.Optional;

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

    // 리뷰 정보 가져오기
    @Override
    public Review getReview(long no) {
        Optional<Review> review = rRepository.findById(no);
        return review.orElse(null);
    }

    // 리뷰 수정하기
    @Override
    public void updateReview(Review review) {
        rRepository.save(review);
    }

    //리뷰 삭제
    @Override
    public void deleteReview(long no) {
        rRepository.deleteById(no);
    }

    
}
