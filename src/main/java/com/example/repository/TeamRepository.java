package com.example.repository;

import java.util.List;

import com.example.entity.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
    
    //팀 전체 조회
    List<Team> findAllByOrderByTeamnoDesc();
}
