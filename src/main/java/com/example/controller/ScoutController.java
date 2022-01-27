package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.Member;
import com.example.entity.Player;
import com.example.entity.Scout;
import com.example.jwt.JwtUtil;
import com.example.service.MemberService;
import com.example.service.PlayerService;
import com.example.service.ScoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
                int Count = sService.checkScout(memberid, pno); // 중복 체크(중복이 없다면 0이 출력됨)
                if(Count != 0){ //선수 중복 체크
                    map.put("status", "선수 중복");
                }
                else{
                    scout.setMember(member);
                    scout.setPlayer(player);
                    sService.insertScout(scout);
                    map.put("status", 200);
                }
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

    // member 별 스카우트 목록 조회
    //127.0.0.1:8080/REST/mscout?page=1
    @RequestMapping(value = "/mscout", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> mscoutGET(
    @RequestParam(value = "page", defaultValue = "1")int page,
    @RequestHeader("token") String token) {
        //페이지 네이션 처리
        PageRequest pageable = PageRequest.of(page-1, 16);
        Map<String, Object> map = new HashMap<>();
        try{
            String memberid = jwtUtil.extractUsername(token); // 토큰을 통해 회원 정보 찾기
            Member member = mService.selectUserOne(memberid); // member 정보 찾기
            if(memberid.equals(member.getUserid())){
                List<Scout> MidScout = sService.getScoutByMember(memberid, pageable);
                map.put("status", 200);
                map.put("scoutlist",MidScout);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    // 스카우터 목록 삭제
    // 127.0.0.1:8080/REST/scoutdelete?sno=
    // 여기서 넘어오는 no는 리뷰 코드
    @RequestMapping(value = "/scoutdelete", method = {RequestMethod.DELETE},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> scoutDELETE(
        @RequestBody Scout scout,
        @RequestParam(name = "sno", defaultValue = "0")long scoutNo,
        @RequestHeader("token") String token){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String memberId = jwtUtil.extractUsername(token); // 토큰을 통해 회원 정보 찾기
            String scoutId = sService.getScoutOne(scoutNo).getMember().getUserid(); // 스카우트 정보에서 member id 찾기
            if(memberId.equals(scoutId)){
                sService.deleteScout(scoutNo);
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

    // member 별 스카우트 숫자 조회
    //127.0.0.1:8080/REST/scoutcount
    @RequestMapping(value = "/scoutcount", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> scoutcountGET(Scout scout,
    @RequestHeader("token") String token) {
        Map<String, Object> map = new HashMap<>();
        try{
            String memberid = jwtUtil.extractUsername(token); // 토큰을 통해 회원 정보 찾기
            Member member = mService.selectUserOne(memberid); // member 정보 찾기
            if(memberid.equals(member.getUserid())){
                Long scoutcount = sService.getScoutCountByUserid(memberid);
                map.put("status", 200);
                map.put("count",scoutcount);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }
}
