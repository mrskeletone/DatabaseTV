package com.example.spring_contract.service;

import com.example.spring_contract.repository.FinishProductRepository;
import com.example.spring_contract.model.FinishedProduct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinishProductService {
    @NonNull
    private FinishProductRepository repository;

    public List<FinishedProduct> getAllProduct() {
        return repository.findAll();
    }

    public List<FinishedProduct> getById(String id) {
        try {
            return repository.findId(Integer.parseInt(id));
        } catch (Exception e) {
            return repository.findAll();
        }
    }

    public List<FinishedProduct> getByName(String name) {
        return repository.findByProduct(name);
    }

    public List<FinishedProduct> getByMan(int man) {
        return repository.findByMan(man);


    }

    public List<FinishedProduct> getByNameOrId(String key) {
        try {
            return repository.findId(Integer.parseInt(key));
        } catch (Exception e) {
            return repository.findByProduct(key);
        }
    }

    public List<FinishedProduct> getByMaterials(String mat) {
        return repository.findByMaterials(mat);
    }

    public List<FinishedProduct> getByDate(LocalDate begin, LocalDate end) {
        if (begin == null) {
            return repository.findAll();
        }
        if (end == null) {
            end = LocalDate.now();
        }
        return repository.findByDate(begin, end);
    }

    public int save(FinishedProduct product) {
        int flag = repository.check(product.getId());
        if (flag == 0 && product.getId() > 0) {
            repository.save(product);
        } else if (flag == 1 || product.getId() < 0) {
            int idNew = repository.findMax() + 1;
            product.setId(idNew);
            repository.save(product);
            return idNew;
        }
        return product.getId();

    }


}
