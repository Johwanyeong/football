package com.example.repository;

import com.example.entity.Agent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long>{
    
}
