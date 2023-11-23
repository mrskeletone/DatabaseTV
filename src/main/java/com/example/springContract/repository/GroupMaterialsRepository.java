package com.example.springContract.repository;

import com.example.springContract.model.FinishedProduct;
import com.example.springContract.model.GroupId;
import com.example.springContract.model.GroupMaterials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMaterialsRepository extends JpaRepository<GroupMaterials, GroupId> {

    List<GroupMaterials> findByFinishedProduct(FinishedProduct finishedProduct);
}
