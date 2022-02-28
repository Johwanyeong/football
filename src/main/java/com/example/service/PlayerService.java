package com.example.service;

import java.util.List;

import com.example.entity.Player;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {
    
    //선수 등록
    public void insertPlayer(Player player);

    //선수 정보 가져오기(선수 등록 번호로 조회하여 가져옴)
    public Player getPlayerOne(Long no);

    //선수 전체 수 조회
    public Long getTotalPage();

    //선수 정보 수정
    public void updatePlayer(Player player);

    //선수 전체 정보 조회
    public List<Player> getPlayerAll(Pageable pageable);

    //팀 번호 별 선수 조회
    public List<Player> getPlayerByTeamno(Long no, Pageable pageable);

    // 팀 번호 별 선수 수 조회
    public Long getTeamPlayerPage(Long no);

    //에이전트 번호 별 선수 조회
    public List<Player> getPlayerByAgentno(Long no, Pageable pageable);

    //몸값 별 선수 조회(내림차순)
    public List<Player> getPlayerALLpriceDESC(Pageable pageable);

    //몸값 별 선수 조회(오름차순)
    public List<Player> getPlayerALLpriceASC(Pageable pageable);

    //포지션 별 선수 조회
    public List<Player> getPlayerALLposition(String position, Pageable pageable);
}
