package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.Agent;
import com.example.service.AgentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {
    
    @Autowired
    AgentService aService;

    //에이전트 전체 조회
    //127.0.0.1:8080/REST/agentall
    @RequestMapping(value = "/agentall", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> teamallGET(Agent agent ) {
        Map<String, Object> map = new HashMap<>();
        try{
            List<Agent> agentAll = aService.getAgentAll();
            map.put("status", "200");
            map.put("agent", agentAll);
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }
}
