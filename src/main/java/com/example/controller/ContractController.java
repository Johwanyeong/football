package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.Contract;
import com.example.entity.Player;
import com.example.entity.Scout;
import com.example.jwt.JwtUtil;
import com.example.service.ContractService;
import com.example.service.MemberService;
import com.example.service.PlayerService;
import com.example.service.ScoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractController {
    
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    ContractService cService;

    @Autowired
    ScoutService sService;

    @Autowired
    MemberService mService;

    @Autowired
    PlayerService pService;

    //계약하기
    // 127.0.0.1:8080/REST/contractinsert?sno=
    @RequestMapping(value = "/contractinsert", method = {RequestMethod.POST},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> contractinsertPOST(
        @ModelAttribute Player player,
        @ModelAttribute Contract contract,
        @RequestParam(name = "sno", defaultValue = "0")long scoutNo,
        @RequestHeader("token") String token){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String memberid = jwtUtil.extractUsername(token); // 토큰을 통해 회원 정보 찾기
            Scout scout = sService.getScoutOne(scoutNo);
            String scoutId = scout.getMember().getUserid(); // scout에서 member 정보 찾기
            if(memberid.equals(scoutId)){ // 로그인 memberid와 scout에서 찾은 memberid가 일치하는지 확인
                Player player2 = scout.getPlayer(); // scout에서 선수 정보 찾기
                player2.setPlayerprice(player.getPlayerprice()); //몸값
                player2.setTeam(player.getTeam()); //소속팀
                pService.updatePlayer(player2);
                System.out.println("----------------------------");
                System.out.println(player.getPlayerprice());
                System.out.println(contract.getScout().getPlayer().getPlayername());
                System.out.println(contract.getScout().getScoutno());
                System.out.println("----------------------------");
                map.put("status", 1);
            }
            else{
                map.put("status", 2);
            }
            if(player.getPlayerprice() != null){ //계약이 성공하면 해당 player의 팀, 몸 값 정보를 수정한다.
                contract.setScout(scout);
                cService.insertContract(contract);
                // 계약이 성공하면 해당 scout의 player 정보를 null로 수정한다.
                scout.setMember(null);
                sService.updateScout(scout);
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
