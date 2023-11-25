package com.example.spring_contract.service;

import com.example.spring_contract.model.Storekeeper;
import com.example.spring_contract.repository.StorekeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorekeeperService {
    @Autowired
    private StorekeeperRepository repository;

    public List<Storekeeper> findByIdOrName(String id){
        List<Storekeeper> list;
        try{
            int i=Integer.parseInt(id);
            list=repository.findByIds(i);
        }catch (Exception e){
            list=repository.findByName(id);
        }
        return list;
    }
    public List<Storekeeper> findBetweenSalary(Optional<Integer> begin, Optional<Integer> end){
        List<Storekeeper> list;
        if(begin.isPresent()&&end.isPresent()){
            list=repository.findBetweenSalary(begin.orElseThrow(),end.orElseThrow());
        } else if (begin.isPresent()) {
            list=repository.findByMinSalary(begin.orElseThrow());
        }else if (end.isPresent()){
            list=repository.findBetweenSalary(0,end.orElseThrow());
        }else{
            list=null;
        }
        return list;
    }
}
