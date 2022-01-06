package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entity.Player;
import com.example.repository.PlayerRepositoy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService{
    
    @Autowired
    PlayerRepositoy pRepositoy;

    //선수 등록
    @Override
    public void insertPlayer(Player player) {
        pRepositoy.save(player);
    }

    //선수 정보 가져오기
    @Override
    public Player getPlayerOne(Long no) {
        Optional<Player> player = pRepositoy.findById(no);
        return player.orElse(null); //선수 정보가 없으면 null 리턴
    }

    //선수 정보 수정
    @Override
    public void updatePlayer(Player player) {
        pRepositoy.save(player);
    }

    //선수 전체 정보 조회
    @Override
    public List<Player> getPlayerAll() {
        return pRepositoy.findAllByOrderByPlayernoDesc();
    }

    //팀 번호 별 선수 조회
    @Override
    public List<Player> getPlayerByTeamno(Long no, Pageable pageable) {
        return pRepositoy.findByTeam_Teamno(no, pageable);
    }

    //에이전트 번호 별 선수 조회
    @Override
    public List<Player> getPlayerByAgentno(Long no, Pageable pageable) {
        return pRepositoy.findByAgent_Agentno(no, pageable);
    }
}
