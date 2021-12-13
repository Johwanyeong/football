package com.example.repository;

import java.awt.print.Pageable;
import java.util.List;

import com.example.entity.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepositoy extends JpaRepository<Player, Long>{
 
    //선수 전체 조회
    List<Player> findAllByOrderByPlayernoDesc();

    //팀 번호 별 선수 조회
    List<Player> findByTeam_TeamnoStartingWithOrderByPlayernoDesc(String teamno, Pageable pageable);


}
