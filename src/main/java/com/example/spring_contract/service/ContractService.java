package com.example.spring_contract.service;

import com.example.spring_contract.model.Client;
import com.example.spring_contract.model.FinishedProduct;
import com.example.spring_contract.model.Manager;
import com.example.spring_contract.model.Sell;
import com.example.spring_contract.repository.ClientRepository;
import com.example.spring_contract.repository.SellRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractService {
    @NonNull
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

    public void save(Client client , Manager manager, FinishedProduct product, Optional<Integer> price
            , Optional<Integer> quantity, LocalDate date){
        int id= repository.findMaxId()+1;
        int p=1;
        if(price.orElseThrow()>0){
            p=price.orElseThrow();
        }
        int q=1;
        if(quantity.orElseThrow()>0){
            q=quantity.orElseThrow();
        }
        if(date.isAfter(LocalDate.now())){
            date=LocalDate.now();
        }
//        Sell sell=new Sell(id,date,p,q,client,manager,product);
//        repository.save(sell);
        repository.saveProc(id,date,product.getId(),manager.getName(),client.getName(),p,q);
    }

}
