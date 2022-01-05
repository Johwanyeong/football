package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entity.Review;
import com.example.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    // 리뷰 전체 정보 조회
    @Override
    public List<Review> getReviewAll() {
        return rRepository.findAllByOrderByReviewnoDesc();
    }

    // 선수 번호 별 리뷰 조회
    @Override
    public List<Review> getReviewByPlayerno(Long no, Pageable pageable) {
        return rRepository.findByPlayer_PlayernoOrderByReviewnoDesc(no, pageable);
    }    
}
