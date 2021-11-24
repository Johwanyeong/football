package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.Team;
import com.example.jwt.JwtUtil;
import com.example.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    TeamService tService;
    
    //팀 등록
    //127.0.0.1:8080/REST/admin/teaminsert
    @RequestMapping(value = "/admin/teaminsert", method = {RequestMethod.POST},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> teaminsertPOST(@RequestBody Team[] team,
        @RequestHeader("token") String token){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            // String adminid = jwtUtil.extractUsername(token); //token을 통해 회원 정보 불러오기
            for(int i =0; i < team.length; i++){
                tService.insertTeam(team[i]);
                map.put("status", 200);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    //에이전트 추가

    //선수 추가

}
