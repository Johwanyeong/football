package com.example.service;

import javax.persistence.EntityManagerFactory;

import com.example.entity.User;
import com.example.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    UserRepository uRepository;

    //회원가입
    @Override
    public void insertUser(User user) {
        uRepository.save(user);
    }
    
}
