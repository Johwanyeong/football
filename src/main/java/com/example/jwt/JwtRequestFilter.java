package com.example.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.security.MemberDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    MemberDetailsService mService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try{
            String token = request.getHeader("token");
            String username=null;
            if(token != null){
                //실제 토큰
                username = jwtUtil.extractUsername(token);
            }
            //username과 시큐리티 세션에 있는 사용자 아이디와 비교
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            //토큰은 전달외었고 로그인이 되어야 되는 것만
            if(username != null && auth == null ) {
                //시큐리티에 로그인 처리 루틴
                UserDetails userDetails = mService.loadUserByUsername(username);
                if(jwtUtil.validateToken(token, userDetails.getUsername()) ){
                    UsernamePasswordAuthenticationToken
                    upat = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                    upat.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request) );
                    SecurityContextHolder.getContext().setAuthentication(upat);
                }
            }
            //컨트롤러로 넘어가는 시점
            filterChain.doFilter(request, response);
        }
        catch(Exception e){
            e.printStackTrace();
            response.sendError(578,"토큰오류");
        }
    }    
}
