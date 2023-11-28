package com.example.spring_contract.service;

import com.example.spring_contract.model.Organization;
import com.example.spring_contract.repository.OrganizationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    @NonNull
    private OrganizationRepository repository;
    public void save(String name,String address){
        Organization organization=new Organization(repository.findMaxId()+1, name,address);
        repository.save(organization);
    }
    public List<Organization> getByOrgAndSup(String org,String sup){
        List<Organization> list;
        try{
            list=repository.findByIds(Integer.parseInt(org));
        }catch (Exception e){
            list=repository.findByName(org);
            if(list.isEmpty()){
                list=repository.findByAddress(org);
            }
        }
        List<Organization> listSup=repository.findByManager(sup);
        List<Organization> copy=new ArrayList<>(list);
        copy.removeAll(listSup);
        list.removeAll(copy);
        return list;
    }
    public List<Organization> getByOrgOrAddressOrId(String key){
        List<Organization>list;
        try{
            list=repository.findByIds(Integer.parseInt(key));
            return list;
        }catch (Exception e){
            list=repository.findByName(key);
            if(list.isEmpty()){
                list=repository.findByAddress(key);
            }
            return list;
        }
    }
}
