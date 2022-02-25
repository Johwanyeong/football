package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.Team;
import com.example.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
    
    @Autowired
    TeamService tService;

    //팀 전체 조회
    //127.0.0.1:8080/REST/teamall
    @RequestMapping(value = "/teamall", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> teamallGET(Team team ) {
        Map<String, Object> map = new HashMap<>();
        try{
            List<Team> teamAll = tService.getTeamAll();
            map.put("status", "200");
            map.put("team", teamAll);
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    //팀 1개 조회
    //127.0.0.1:8080/REST/teamone
    @RequestMapping(value = "/teamone", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> teamoneGET(Team team
    ) {
        Map<String, Object> map = new HashMap<>();
        try{
            long no = team.getTeamno();
            Team team2 = tService.getTeamOne(no);
            map.put("status", "200");
            map.put("team", team2);
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }
}
