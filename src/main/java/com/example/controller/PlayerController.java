package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.Player;
import com.example.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @Autowired
    PlayerService pService;

    // 선수 1명 조회
    //127.0.0.1:8080/REST/playerone?no=
    @RequestMapping(value = "/playerone", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> playeroneGET(Player player,
    @RequestParam("no") long no) {
        Map<String, Object> map = new HashMap<>();
        try{
            Player player2 = pService.getPlayerOne(no);
            map.put("status", "200");
            map.put("player",player2 );
            map.put("playerimg","/REST/playeroneimg?no=" + no );    //선수 이미지 url
            map.put("teamname", player2.getTeam().getTeamname());
            map.put("agentname", player2.getAgent().getAgentname());
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    // 선수 1명 사진 조회
    //127.0.0.1:8080/REST/playeroneimg?no=
    @RequestMapping(value = "/playeroneimg", method = RequestMethod.GET)
    public ResponseEntity<byte[]> playeroneimgGET(
    @RequestParam("no") long no) {
        try{
            Player player = pService.getPlayerOne(no);
            if(player.getImage() != null) {
                HttpHeaders headers = new HttpHeaders();
                if(player.getImagetype().equals("image/jpeg")){
                    headers.setContentType(MediaType.IMAGE_JPEG);
                }
                else if(player.getImagetype().equals("image/png")){
                    headers.setContentType(MediaType.IMAGE_PNG);
                }
                else if(player.getImagetype().equals("image/gif")){
                    headers.setContentType(MediaType.IMAGE_GIF);
                }
                ResponseEntity<byte[]> response = 
                    new ResponseEntity<>(player.getImage(), headers, HttpStatus.OK);
                return response;
            }
            return null;
            
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 선수 전체 조회
    //127.0.0.1:8080/REST/playerall?page=1
    @RequestMapping(value = "/playerall", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> playerallGET(Player player,
    @RequestParam(value = "page", defaultValue = "1")int page ) {
        //페이지 네이션 처리
        PageRequest pageable = PageRequest.of(page-1, 15);
        Map<String, Object> map = new HashMap<>();
        try{
            List<Player> playerAll = pService.getPlayerAll(pageable);
            Long totalpage = pService.getTotalPage(); //등록된 전체 선수 수 조회
            map.put("status", "200");
            map.put("count", playerAll.size()); //1 페이지에 표시된 선수 숫자
            map.put("totalpage", (totalpage-1)/15+1 ); //전체 페이지
            map.put("player",playerAll );
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    //전체 선수 숫자 조회
    //127.0.0.1:8080/REST/playercount
    @RequestMapping(value = "/playercount", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> playercountGET(Player player) {
        Map<String, Object> map = new HashMap<>();
        try{
            Long totalpage = pService.getTotalPage(); //등록된 전체 선수 수 조회
            map.put("status", "200");
            map.put("count", totalpage);
            map.put("totalpage", (totalpage-1)/15+1 ); //전체 페이지
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }
    
    //팀 번호 별 선수 조회
    // 127.0.0.1:8080/REST/bnoplayer?page=1&bno=
    @RequestMapping(value = "/bnoplayer", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> BnoplayerGET(Player player,
    @RequestParam(value = "page", defaultValue = "1")int page,
    @RequestParam(name = "bno")long bno) {
        //페이지 네이션 처리
        PageRequest pageable = PageRequest.of(page-1, 16);
        Map<String, Object> map = new HashMap<>();
        try{
            List<Player> Bnoplayer = pService.getPlayerByTeamno(bno, pageable);
            map.put("status", "200");
            map.put("player",Bnoplayer );
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    //에이전트 번호 별 선수 조회
    // 127.0.0.1:8080/REST/bnoplayer?page=1&ano=
    @RequestMapping(value = "/anoplayer", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> AnoplayerGET(Player player,
    @RequestParam(value = "page", defaultValue = "1")int page,
    @RequestParam(name = "ano")long ano) {
        //페이지 네이션 처리
        PageRequest pageable = PageRequest.of(page-1, 16);
        Map<String, Object> map = new HashMap<>();
        try{
            List<Player> Bnoplayer = pService.getPlayerByAgentno(ano, pageable);
            map.put("status", "200");
            map.put("player",Bnoplayer );
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    //몸값 별 선수 조회(내림차순)
    //127.0.0.1:8080/REST/playerpricedesc?page=1
    @RequestMapping(value = "/playerpricedesc", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> playerpricedescGET(Player player,
    @RequestParam(value = "page", defaultValue = "1")int page ) {
        //페이지 네이션 처리
        PageRequest pageable = PageRequest.of(page-1, 15);
        Map<String, Object> map = new HashMap<>();
        try{
            List<Player> PlayerALLpriceDESC = pService.getPlayerALLpriceDESC(pageable);
            map.put("status", "200");
            map.put("count", PlayerALLpriceDESC.size());
            map.put("player", PlayerALLpriceDESC);
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    //몸값 별 선수 조회(오름차순)
    //127.0.0.1:8080/REST/playerpriceasc
    @RequestMapping(value = "/playerpriceasc", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> playerpriceascGET(Player player,
    @RequestParam(value = "page", defaultValue = "1")int page  ) {
        //페이지 네이션 처리
        PageRequest pageable = PageRequest.of(page-1, 15);
        Map<String, Object> map = new HashMap<>();
        try{
            List<Player> PlayerALLpriceASC = pService.getPlayerALLpriceASC(pageable);
            map.put("status", "200");
            map.put("player", PlayerALLpriceASC);
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    //포지션 별 선수 조회
    //127.0.0.1:8080/REST/playerposition?page=1&position=
    @RequestMapping(value = "/playerposition", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> playerpositionGET(Player player,
    @RequestParam(value = "page", defaultValue = "1")int page,
    @RequestParam(value = "position", defaultValue = "")String position) {
        //페이지 네이션 처리
        PageRequest pageable = PageRequest.of(page-1, 15);
        Map<String, Object> map = new HashMap<>();
        try{
            List<Player> PlayerPosition = pService.getPlayerALLposition(position, pageable);
            map.put("status", "200");
            map.put("player", PlayerPosition);
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }
}
