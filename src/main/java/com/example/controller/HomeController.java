package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping(value = "/home", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> homeGET(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        return map;
    }
    
}
