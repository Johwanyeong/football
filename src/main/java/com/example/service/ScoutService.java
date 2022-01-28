package com.example.service;

import java.util.List;

import com.example.entity.Scout;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ScoutService {
    
    // 스카우트 추가
    public void insertScout(Scout scout);

    // member 별 스카우트 목록 조회
    public List<Scout> getScoutByMember(String id, Pageable pageable);

    // 스카우터 목록 1개 조회
    public Scout getScoutOne(Long no);

    // 스카우터 목록 삭제
    public void deleteScout(Long no);

    // 스카우터 목록 삭제2
    public void deleteScout2(Scout scout);

    // 스카우터 목록 수정
    public void updateScout(Scout scout);

    // 스카우트 member, 선수 중복 조회
    public int checkScout(String id, Long no);

    // member 별 스카우트 목록 숫자 조회
    public Long getScoutCountByUserid(String id);
    
}
