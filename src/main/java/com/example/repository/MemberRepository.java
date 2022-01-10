package com.example.repository;

import com.example.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, String>{

    // userid 중복 조회
    @Query(value = "SELECT COUNT(USERID) FROM USER WHERE USERID =:userid", nativeQuery = true)
    public int queryCheckUserid(String userid);
}
