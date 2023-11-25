package com.example.spring_contract.repository;

import com.example.spring_contract.model.FinishedProduct;
import com.example.spring_contract.model.GroupId;
import com.example.spring_contract.model.GroupMaterials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMaterialsRepository extends JpaRepository<GroupMaterials, GroupId> {

    List<GroupMaterials> findByFinishedProduct(FinishedProduct finishedProduct);
}
