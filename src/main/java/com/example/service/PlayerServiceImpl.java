package com.example.service;

import com.example.entity.Player;
import com.example.repository.PlayerRepositoy;

import org.springframework.beans.factory.annotation.Autowired;
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
}
