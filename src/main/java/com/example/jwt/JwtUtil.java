package com.example.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//토큰발행(만료시간), 토큰의 유효성 검사, 토큰과 관련된 메소드
@Service
public class JwtUtil {

    //토큰 생성용 보안키
    private final String SECRETKEY = "asdfjk";
    
     //정보 추출용 메소드
     private<T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    //토큰생성(아이디 정보를 이용한 토큰 생성)
    public String generateToken(String username){
        // long tokenValidTime = 1000 * 60 * 30;   //30분
        long tokenValidTime = 1000 * 60 * 60 * 4; //4시간
        Map<String, Object> claims = new HashMap<>();
        String token =Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))  //현재 시간
            .setExpiration(new Date(System.currentTimeMillis() + tokenValidTime))   //만료시간
            .signWith(SignatureAlgorithm.HS256, SECRETKEY)
            .compact();
        return token;
    }

     //토큰 유효성 확인
    // vue에서 오는 토큰에서 꺼낸 아이디 정보와
    // 시큐리티 세션에 보관되어 있던 아이디 정보
    public Boolean validateToken(String token, String userid){
        //토큰에서 아이디 정보 추출
        final String username = this.extractUsername(token);
        //vue에서 오는 토큰에서 꺼낸 아이디 정보와
        //시큐리티 세션에 보관되어 있던 아이디 정보
        if(username.equals(userid) && !isTokenExpired(token) ){
            return true;
        }
        return false;
    }

    //토큰에서 아이디 정보 추출하기
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //토큰에서 만료 시간 추출하기
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
        //토큰의 만료시간이 유효한지 확인
        public Boolean isTokenExpired(String token){
            //만료시간 가져와서 현재시간보다 이전인지 확인
            return this.extractExpiration(token).before(new Date());
        }
}
