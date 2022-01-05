package com.example.repository;

import java.util.List;

import com.example.entity.Review;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
    
    //리뷰 전체 조회
    List<Review> findAllByOrderByReviewnoDesc();

    //선수 번호 별 리뷰 조회
    List<Review> findByPlayer_PlayernoOrderByReviewnoDesc(Long playerno, Pageable pageable);
}
