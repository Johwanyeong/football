package com.example.service;

import java.util.List;

import com.example.entity.Review;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    
    // 리뷰 등록
    public void insertReview(Review review);

    // 리뷰 정보 가져오기
    public Review getReview(long no);

    // 리뷰 수정
    public void updateReview(Review review);

    // 리뷰 삭제
    public void deleteReview(long no);

    // 리뷰 전체 정보 조회
    public List<Review> getReviewAll();

    // 선수 번호 별 리뷰 조회
    public List<Review> getReviewByPlayerno(Long no, Pageable pageable);

    //선수 번호 별 리뷰 숫자 조회
    public Long getReviewByPlayernoCount(Long no);
}
