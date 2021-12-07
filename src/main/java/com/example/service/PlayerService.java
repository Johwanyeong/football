package com.example.service;

import com.example.entity.Player;

import org.springframework.stereotype.Service;

@Service
public interface PlayerService {
    
    //선수 등록
    public void insertPlayer(Player player);

    //선수 정보 가져오기(선수 등록 번호로 조회하여 가져옴)
    public Player getPlayerOne(Long no);

    //선수 정보 수정
    public void updatePlayer(Player player);
}
