package com.example.service;

import com.example.entity.Agent;

import org.springframework.stereotype.Service;

@Service
public interface AgentService {

    //에이전트 등록
    public Agent insertAgent(Agent agent);
    
}
