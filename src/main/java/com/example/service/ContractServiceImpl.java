package com.example.service;

import com.example.entity.Contract;
import com.example.repository.ContractRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService{
    
    @Autowired
    ContractRepository cRepository;

    //계약하기
    @Override
    public void insertContract(Contract contract) {
        cRepository.save(contract);
    }
}
