package com.example.spring_contract.service;

import com.example.spring_contract.model.Organization;
import com.example.spring_contract.model.Suppliers;
import com.example.spring_contract.repository.SuppliersRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SuppliersService {
    @NonNull
    private SuppliersRepository repository;
    public List<Suppliers> getByIdOrName(String key){
        try {
            return repository.findByIds(Integer.parseInt(key));
        }catch (Exception e){
            return repository.findByName(key);
        }
    }
    public List<Suppliers> getByIdAndOrg(String id,String org){
        List<Suppliers> list=getByIdOrName(id);
        List<Suppliers> listOrg=repository.findByOrg(org);
        List<Suppliers> listAddress=repository.findByAddress(org);
        List<Suppliers> copy=new ArrayList<>(list);
        if(!listOrg.isEmpty()){
            copy.removeAll(listOrg);
        }else{
            copy.removeAll(listAddress);
        }
        list.removeAll(copy);
        return list;
    }
    public List<Suppliers> getAllSup(){
        return repository.findAll();
    }

    public List<Suppliers> getByMat(String mat){
        return repository.findByMat(mat);
    }
    public List<Suppliers> getByOrgOrAddress(String key){
        List<Suppliers> list=repository.findByOrg(key);
        if (list.isEmpty()) {
            list = repository.findByAddress(key);
        }
        return list;

    }
    public void save(String name, Organization organization){
        Suppliers suppliers=new Suppliers(repository.findMaxId()+1, name,organization);
        repository.save(suppliers);
    }
}
