package com.example.spring_contract.service;

import com.example.spring_contract.model.Sell;
import com.example.spring_contract.repository.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    @Autowired
    private SellRepository repository;
    public List<Sell> getAll(){
        return  repository.findAll();
    }

    public List<Sell> getByManager(String man){
        return repository.findByMan(man);
    }

    public List<Sell> getByClient(String client){
        return repository.findByCl(client);
    }

    public List<Sell> getByProduct(String product){
        return repository.findByProduct(product);
    }

    public List<Sell> getByPrice(int begin ,int end){
        return repository.findByPrice(begin,end);
    }
    //Цена от суммы begin
    public List<Sell> getByBeginPrice(int begin){
        return repository.findByBeginPrice(begin);
    }

    public List<Sell> getById(int id){
        return repository.findByIds(id);
    }
}
