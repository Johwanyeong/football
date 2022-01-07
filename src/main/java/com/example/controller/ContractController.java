package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.Contract;
import com.example.entity.Member;
import com.example.entity.Scout;
import com.example.jwt.JwtUtil;
import com.example.service.ContractService;
import com.example.service.MemberService;
import com.example.service.ScoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    //계약하기
    // 127.0.0.1:8080/REST/contractinsert
    @RequestMapping(value = "/contractinsert", method = {RequestMethod.POST},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> contractinsertPOST(
        @RequestBody Contract contract,
        @RequestHeader("token") String token){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String memberid = jwtUtil.extractUsername(token); // 토큰을 통해 회원 정보 찾기
            Long sno = contract.getScout().getScoutno(); // scout 정보 찾기
            Scout scout = sService.getScoutOne(sno);
            String scoutId = sService.getScoutOne(sno).getMember().getUserid(); // scout에서 member 정보 찾기
            if(memberid.equals(scoutId)){ // 로그인 memberid와 scout에서 찾은 memberid가 일치하는지 확인
                contract.setScout(scout);
                cService.insertContract(contract);
                if(contract.getContractno() != null){
                    // System.out.println(sno);
                    // 계약이 성공하면 해당 scout의 정보를 null로 수정한다.
                    scout.setPlayer(null);
                    sService.updateScout(scout);
                }
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