package com.example.spring_contract.service;

import com.example.spring_contract.model.Suppliers;
import com.example.spring_contract.repository.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuppliersService {
    @Autowired
    private SuppliersRepository repository;
    public List<Suppliers> getByIdOrOrg(String key){
        try {
            return repository.findByIds(Integer.parseInt(key));
        }catch (Exception e){
            return repository.findByOrg(key);
        }
    }

    public List<Suppliers> getAllSup(){
        return repository.findAll();
    }

    public List<Suppliers> getByMat(String mat){
        return repository.findByMat(mat);
    }
}
