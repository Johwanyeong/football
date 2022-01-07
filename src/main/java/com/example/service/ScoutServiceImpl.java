package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entity.Scout;
import com.example.repository.ScoutRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    // member 별 스카우트 목록 조회
    @Override
    public List<Scout> getScoutByMember(String id, Pageable pageable) {
        return sRepository.findByMember_Userid(id, pageable);
    }

    // 스카우터 목록 1개 조회
    @Override
    public Scout getScoutOne(Long no) {
        Optional<Scout> scout = sRepository.findById(no);
        return scout.orElse(null);
    }

    // 스카우터 목록 삭제
    @Override
    public void deleteScout(Long no) {
        sRepository.deleteById(no);
    }

    // 스카우터 목록 수정
    @Override
    public void updateScout(Scout scout) {
        sRepository.save(scout);
    }

    // 스카우트 member 중복 조회
    @Override
    public int checkScout(String id, Long no) {
        return sRepository.queryCheckMember(id, no);
    }

}
