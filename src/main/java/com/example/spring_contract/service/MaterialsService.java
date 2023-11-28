package com.example.spring_contract.service;

import com.example.spring_contract.model.Materials;
import com.example.spring_contract.model.Suppliers;
import com.example.spring_contract.repository.MaterialsRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaterialsService {
    @NonNull
    private MaterialsRepository repository;
    public void save(Suppliers suppliers, String name, Optional<Integer> price, Optional<Integer> quantity){
        int idMat=repository.findMaxId()+1;
        Materials materials=new Materials(idMat,name,quantity.orElseThrow(), price.orElseThrow(),suppliers);
        repository.save(materials);
    }
}
