package com.example.service;

import java.util.List;

import com.example.entity.Team;

import org.springframework.stereotype.Service;

@Service
public interface TeamService {

    //팀 등록
    public Team insertTeam(Team team);
    
    //팀 전체 조회
    public List<Team> getTeamAll();

}
