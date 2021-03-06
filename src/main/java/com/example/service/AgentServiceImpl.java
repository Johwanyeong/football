package com.example.service;

import java.util.List;

import com.example.entity.Agent;
import com.example.repository.AgentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentServiceImpl implements AgentService{

    @Autowired
    AgentRepository aRepository;

    //에이전트 등록
    @Override
    public Agent insertAgent(Agent agent) {
        return aRepository.save(agent);
    }

    //에이전트 전체 조회
    @Override
    public List<Agent> getAgentAll() {
        return aRepository.findAllByOrderByAgentnoDesc();
    }
    
}
