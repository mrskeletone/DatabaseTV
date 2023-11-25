package com.example.spring_contract.service;

import com.example.spring_contract.model.Storekeeper;
import com.example.spring_contract.model.TV_warehouse;
import com.example.spring_contract.repository.StorekeeperRepository;
import com.example.spring_contract.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository repository;
@Autowired
private StorekeeperRepository storekeeperRepository;
    public List<TV_warehouse> getAllWarehouse() {
        return repository.findAll();
    }

    public List<TV_warehouse> getByName(String name) {
        return repository.findByName(name);
    }

    public List<TV_warehouse> getBysId(String id) {
        try {
            return repository.findBysId(Integer.parseInt(id));
        } catch (Exception e) {
            return repository.findAll();
        }
    }

    public List<TV_warehouse> getByProduct(String product) {
        return repository.findByProduct(product);
    }

    public void save(TV_warehouse warehouse) {
        int flag = repository.check(warehouse.getId());
        if (flag == 0 && warehouse.getId() > 0) {
            repository.save(warehouse);
        } else if (flag == 1 || warehouse.getId() < 0) {
            int idNew = repository.findMax() + 1;
            warehouse.setId(idNew);
            repository.save(warehouse);
        }
    }
    public void saveStorekeeper(Storekeeper storekeeper) {
        int flag = storekeeperRepository.check(storekeeper.getId());
        if (flag == 0 && storekeeper.getId() > 0) {
            storekeeperRepository.save(storekeeper);
        } else if (flag == 1 || storekeeper.getId() < 0) {
            int idNew = storekeeperRepository.findMax() + 1;
            storekeeper.setId(idNew);
            storekeeperRepository.save(storekeeper);
        }
    }
}
