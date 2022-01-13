package com.example.repository;

import java.util.List;

import com.example.entity.Agent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long>{
    
    //에이전트 전체 조회
    List<Agent> findAllByOrderByAgentnoDesc();
}
