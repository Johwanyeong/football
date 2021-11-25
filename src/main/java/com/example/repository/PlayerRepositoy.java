package com.example.repository;

import com.example.entity.Player;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepositoy extends JpaRepository<Player, Long>{
    
}
