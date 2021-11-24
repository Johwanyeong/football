package com.example.service;

import com.example.entity.Team;

import org.springframework.stereotype.Service;

@Service
public interface TeamService {

    //팀 등록
    public Team insertTeam(Team team);
    
}
