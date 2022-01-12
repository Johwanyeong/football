package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.Member;
import com.example.jwt.JwtUtil;
import com.example.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    MemberService mService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    //중복 확인
    //127.0.0.1:8080/REST/member/check
    @RequestMapping(value = "/member/check", method = {RequestMethod.POST},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> checkPOST(@RequestBody Member member){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String userid = member.getUserid();
            int Count = mService.checkUserid(userid);
            if(Count != 0){ //user 중복 체크
                map.put("status", "id 중복");
                map.put("check", 1);
            }
            else{
                map.put("status", "사용 가능");
                map.put("check", 0);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    //회원가입
    //127.0.0.1:8080/REST/member/join
    @RequestMapping(value = "/member/join", method = {RequestMethod.POST},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> joinPOST(@RequestBody Member member){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
            String userid = member.getUserid(); 
            int Count = mService.checkUserid(userid);   //중복 체크(중복이 없다면 0 출력)
            if(Count != 0){
                map.put("status", "id 중복");
                map.put("check", 1);
            }
            else{
                member.setUserpw(bcpe.encode(member.getUserpw()));
                member.setUserrole("USER"); //관리자는 1명이고 이후엔 전부 USER이므로 ROLE에 USER로 SET 한다.
                mService.insertUser(member);
                map.put("status", 200);
            } 
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    //로그인
    //127.0.0.1:8080/REST/member/login
    @RequestMapping(value = "/member/login", method = {RequestMethod.POST},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> loginPOST(@RequestBody Member member) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                    member.getUserid(), member.getUserpw()));
            String role = mService.selectUserOne(member.getUserid()).getUserrole();
            map.put("result", 200);
            map.put("role", role);
            map.put("token", jwtUtil.generateToken(member.getUserid()));
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", e.hashCode());
        }
        return map;
    }

    //토큰을 통한 권한 확인
    //127.0.0.1:8080/REST/member/role
    @RequestMapping(value = "/member/role", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> roleGET(
        @RequestHeader("token") String token) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            String userid = jwtUtil.extractUsername(token);
            Member member = mService.selectUserOne(userid);
            if(member != null){
                String role = member.getUserrole();
                map.put("role", role);
                map.put("result", 200);
            }
            else{
                map.put("result", 300);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", e.hashCode());
        }
        return map;
    }

    //회원 정보 수정
    //127.0.0.1:8080/REST/member/update
    @RequestMapping(value = "/member/update", method = {RequestMethod.PUT},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updatePUT(@RequestBody Member member,
        @RequestHeader("token") String token) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            String userid = jwtUtil.extractUsername(token);
            Member member2 = mService.selectUserOne(userid);
            member2.setUserid(member.getUserid());
            member2.setUsername(member.getUsername());
            mService.updateUser(member2);
            map.put("result", 200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", e.hashCode());
        }
        return map;
    }
}
