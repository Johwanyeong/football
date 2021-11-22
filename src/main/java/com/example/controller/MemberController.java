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

    //회원가입
    //127.0.0.1:8080/REST/member/join
    @RequestMapping(value = "/member/join", method = {RequestMethod.POST},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> joinPOST(@RequestBody Member member){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
            member.setUserpw(bcpe.encode(member.getUserpw()));
            mService.insertUser(member);
            map.put("status", 200);
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    //로그인
    //127.0.0.1:8080/REST/member/login
    @RequestMapping(value = "member/login", method = {RequestMethod.POST},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> loginPOST(@RequestBody Member member) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                    member.getUserid(), member.getUserpw()));
            map.put("result", 1L);
            map.put("token", jwtUtil.generateToken(member.getUserid()));
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", e.hashCode());
        }
        return map;
    }
}
