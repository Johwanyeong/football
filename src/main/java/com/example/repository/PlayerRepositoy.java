package com.example.repository;

import java.util.List;

import com.example.entity.Player;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepositoy extends JpaRepository<Player, Long>{
 
    //선수 전체 조회
    List<Player> findAllByOrderByPlayernoDesc();

    //팀 번호 별 선수 조회
    // List<Player> findByTeam_TeamnoOrderByPlayernoDesc(Long teamno, Pageable pageable);
    List<Player> findByTeam_Teamno(Long teamno, Pageable pageable);

    //에이전트 번호 별 선수 조회
    // List<Player> findByAgent_AgentnoOrderByPlayernoDesc(Long agentno, Pageable pageable);
    List<Player> findByAgent_Agentno(Long agentno, Pageable pageable);

}
