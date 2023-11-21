package com.example.springContract.service;

import com.example.springContract.model.TV_warehouse;
import com.example.springContract.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository repository;

public List<TV_warehouse> getAllWarehouse(){
    return repository.findAll();
}

public  List<TV_warehouse> getByName(String name){
return repository.findByName(name);
}
    public  List<TV_warehouse> getBysId(String id){
    try {
        return repository.findBysId(Integer.parseInt(id));}
    catch (Exception e){
      return repository.findAll();
    }
    }
    public List<TV_warehouse> getByProduct(String product){
    return repository.findByProduct(product);
    }
}
