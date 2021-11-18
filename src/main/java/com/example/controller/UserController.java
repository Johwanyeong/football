package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.User;
import com.example.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService uService;

    //회원가입
    //127.0.0.1:8080/REST/user/join
    @RequestMapping(value = "/user/join", method = {RequestMethod.POST},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> joinGET(@RequestBody User user){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
            user.setUserpw(bcpe.encode(user.getUserpw()));
            uService.insertUser(user);
            map.put("status", 200);
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }
}
