package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entity.Team;
import com.example.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    TeamRepository tRepository;
    
    //팀 등록
    @Override
    public Team insertTeam(Team team) {
        return tRepository.save(team);
    }

    //팀 전체 조회
    @Override
    public List<Team> getTeamAll() {
        return tRepository.findAllByOrderByTeamnoDesc();
    }

    //팀 1개 조회
    @Override
    public Team getTeamOne(Long no) {
        Optional<Team> team = tRepository.findById(no);
        return team.orElse(null);
    }
    
    
}
