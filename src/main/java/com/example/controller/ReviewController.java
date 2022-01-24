package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.Member;
import com.example.entity.Player;
import com.example.entity.Review;
import com.example.jwt.JwtUtil;
import com.example.service.MemberService;
import com.example.service.PlayerService;
import com.example.service.ReviewService;

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

    // 리뷰 1개 조회
    //127.0.0.1:8080/REST/reviewone?no=
    @RequestMapping(value = "/reviewone", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> reviewoneGET(Review review,
    @RequestParam("no") long no) {
        Map<String, Object> map = new HashMap<>();
        try{
            Review review2 = rService.getReview(no);
            map.put("status", "200");
            map.put("review",review2 );
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    //리뷰 전체 정보 조회
    //127.0.0.1:8080/REST/reviewall
    @RequestMapping(value = "/reviewall", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> reviewallGET(Review review) {
        Map<String, Object> map = new HashMap<>();
        try{
            List<Review> reviewAll = rService.getReviewAll();
            map.put("status", "200");
            map.put("review",reviewAll );
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    // 선수 번호 별 리뷰 조회
    //127.0.0.1:8080/REST/pnoreview?page=1&pno=
    @RequestMapping(value = "/pnoreview", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> pnoreviewGET(Review review,
    @RequestParam(value = "page", defaultValue = "1")int page,
    @RequestParam(value = "pno")long pno) {
        //페이지 네이션 처리
        PageRequest pageable = PageRequest.of(page-1, 16);
        Map<String, Object> map = new HashMap<>();
        try{
            List<Review> Pnoreview = rService.getReviewByPlayerno(pno, pageable);
            map.put("status", "200");
            map.put("review",Pnoreview );
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

     // 선수 번호 별 리뷰 숫자 조회
     //127.0.0.1:8080/REST/pnoreviewcount?pno=
    @RequestMapping(value = "/pnoreviewcount", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> pnoreviewcountGET(Review review,
    @RequestParam(value = "pno")long pno) {
        Map<String, Object> map = new HashMap<>();
        try{
            Long reviewcount = rService.getReviewByPlayernoCount(pno); //선수 번호 별 리뷰 수 조회
            map.put("status", "200");
            map.put("count", reviewcount );
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }


}
