package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.Member;
import com.example.entity.Player;
import com.example.entity.Review;
import com.example.jwt.JwtUtil;
import com.example.service.MemberService;
import com.example.service.PlayerService;
import com.example.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @Autowired
    JwtUtil jwtUtil;
    
    @Autowired
    ReviewService rService;

    @Autowired
    MemberService mService;

    @Autowired
    PlayerService pService;

    // 리뷰 등록
    //127.0.0.1:8080/REST/reviewinsert?pno=
    @RequestMapping(value = "/reviewinsert", method = {RequestMethod.POST},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> reviewinsertPOST(
        @RequestBody Review review,
        @RequestParam(name = "pno", defaultValue = "0")long playerNo,
        @RequestHeader("token") String token){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String memberid = jwtUtil.extractUsername(token); // 토큰을 통해 회원 정보 찾기
            Member member = mService.selectUserOne(memberid); // member 정보 찾기
            Player player = pService.getPlayerOne(playerNo); //player 정보 찾기
            if(memberid.equals(member.getUserid())){
                review.setMember(member);
                review.setPlayer(player);
                rService.insertReview(review);
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

    //리뷰 수정
    //127.0.0.1:8080/REST/reviewupdate?rno=
    @RequestMapping(value = "/reviewupdate", method = {RequestMethod.PUT},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> reviewupdatePUT(
        @RequestBody Review review,
        @RequestParam(name = "rno", defaultValue = "0")long reviewNo,
        @RequestHeader("token") String token){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String memberid = jwtUtil.extractUsername(token); // 토큰을 통해 회원 정보 찾기
            String reviewid = rService.getReview(reviewNo).getMember().getUserid(); // 리뷰 정보에서 member id 찾기
            if(memberid.equals(reviewid)){
                Review review2 = rService.getReview(reviewNo);
                review2.setContent(review.getContent());
                review2.setRating(review.getRating());
                rService.insertReview(review2);
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

    //리뷰 삭제
    //127.0.0.1:8080/REST/reviewdelete?rno=
    @RequestMapping(value = "/reviewdelete", method = {RequestMethod.DELETE},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> reviewDELETE(
        @RequestBody Review review,
        @RequestParam(name = "rno", defaultValue = "0")long reviewNo,
        @RequestHeader("token") String token){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String memberid = jwtUtil.extractUsername(token); // 토큰을 통해 회원 정보 찾기
            String reviewid = rService.getReview(reviewNo).getMember().getUserid(); // 리뷰 정보에서 member id 찾기
            if(memberid.equals(reviewid)){
                rService.deleteReview(review.getReviewno());
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
