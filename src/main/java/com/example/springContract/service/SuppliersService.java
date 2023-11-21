package com.example.springContract.service;

import com.example.springContract.model.Suppliers;
import com.example.springContract.repository.SuppliersRepository;
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
