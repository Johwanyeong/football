package com.example.repository;

import java.util.List;

import com.example.entity.Scout;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoutRepository extends JpaRepository<Scout, Long>{
    
    // member 별 스카우트 목록 조회
    List<Scout> findByMember_Userid(String userid, Pageable pageable);
}
