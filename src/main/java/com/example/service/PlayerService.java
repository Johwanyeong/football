package com.example.service;

import com.example.entity.Player;

import org.springframework.stereotype.Service;

@Service
public interface PlayerService {
    
    //선수 등록
    public void insertPlayer(Player player);
}
