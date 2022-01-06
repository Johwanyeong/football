package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.Member;
import com.example.entity.Player;
import com.example.entity.Scout;
import com.example.jwt.JwtUtil;
import com.example.service.MemberService;
import com.example.service.PlayerService;
import com.example.service.ScoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoutController {
    
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    ScoutService sService;

    @Autowired
    MemberService mService;

    @Autowired
    PlayerService pService;

    // 스카우트 목록 추가
    // 127.0.0.1:8080/REST/scoutinsert
    @RequestMapping(value = "/scoutinsert", method = {RequestMethod.POST},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> scoutinsertPOST(
        @RequestBody Scout scout,
        @RequestHeader("token") String token){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String memberid = jwtUtil.extractUsername(token); // 토큰을 통해 회원 정보 찾기
            Member member = mService.selectUserOne(memberid); // member 정보 찾기
            if(memberid.equals(member.getUserid())){
                Long pno = scout.getPlayer().getPlayerno();
                Player player = pService.getPlayerOne(pno); // player 정보 찾기
                scout.setMember(member);
                scout.setPlayer(player);
                sService.insertScout(scout);
                map.put("status", 200);
            }
            else{
                map.put("status", 300);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

}
