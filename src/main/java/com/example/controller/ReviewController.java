package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.Review;
import com.example.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    
    @Autowired
    ReviewService rService;

    // 리뷰 등록
    //127.0.0.1:8080/REST/reviewinsert
    @RequestMapping(value = "/reviewinsert", method = {RequestMethod.POST},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> playerinsertPOST(
        @RequestBody Review review,
        @RequestHeader("token") String token){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            rService.insertReview(review);
            map.put("status", 200);
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }
}
