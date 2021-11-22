package com.example.security;

import com.example.jwt.JwtRequestFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration  //환경설정 파일
@EnableWebSecurity  //security를 적용
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    MemberDetailsService mService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

     //환경 설정에서 객체만들기
    //회원가입시 암호 방식을 로그인 시 같은 방식으롤 적용해야 하기 때문에
    @Bean
    public BCryptPasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }

    //인증방식은 MemberDetailsService를 사용하고,
    //암호화 방식은 위에서 만든 @Bean 객체 방식으로 사용 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mService).passwordEncoder(encode());
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //권한 설정
        http.authorizeRequests()
            //관리자 로그인
            .antMatchers("/admin","/admin/*")
            // //회원 로그인
            // .antMatchers("/member","/member/*")
            .hasAnyRole("ADMIN","USER").anyRequest().permitAll();
        //필터 추가하기
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        //session 저장 방법
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //h2 console 사용
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
    }
}
