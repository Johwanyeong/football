package com.example.service;

import com.example.entity.Contract;

import org.springframework.stereotype.Service;

@Service
public interface ContractService {

    //계약하기
    public void insertContract(Contract contract);
    
}
