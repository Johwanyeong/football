package com.example.repository;

import java.util.List;

import com.example.entity.Player;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepositoy extends JpaRepository<Player, Long>{
 
    //선수 전체 조회
    List<Player> findAllByOrderByPlayernoDesc(Pageable pageable);

    //선수 전체 수 조회
    Long countAllByOrderByPlayernoDesc();

    //팀 번호 별 선수 조회
    // List<Player> findByTeam_TeamnoOrderByPlayernoDesc(Long teamno, Pageable pageable);
    List<Player> findByTeam_Teamno(Long teamno, Pageable pageable);

    //에이전트 번호 별 선수 조회
    // List<Player> findByAgent_AgentnoOrderByPlayernoDesc(Long agentno, Pageable pageable);
    List<Player> findByAgent_Agentno(Long agentno, Pageable pageable);

    //몸값 별 선수 조회(내림차순)
    @Query(value = "SELECT * FROM PLAYER ORDER BY PLAYERPRICE DESC", nativeQuery = true)
    public List<Player> queryListPlayerDESC(Pageable pageable);

    //몸값 별 선수 조회(오름차순)
    @Query(value = "SELECT * FROM PLAYER ORDER BY PLAYERPRICE", nativeQuery = true)
    public List<Player> queryListPlayerASC(Pageable pageable);

    //포지션 별 선수 조회
    @Query(value = "SELECT * FROM PLAYER WHERE PLAYERPOSITION = :position", nativeQuery = true)
    public List<Player> queryListPlayerPosition(@Param("position") String position, Pageable pageable);

}
