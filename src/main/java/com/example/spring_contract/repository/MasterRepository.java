package com.example.spring_contract.repository;

import com.example.spring_contract.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MasterRepository extends JpaRepository<Master,Integer> {
    @Query(nativeQuery = true,value = "select MAX(id_Master) from master ")
    int findMax();
    @Query(nativeQuery = true,value = "select count(*) from master where id_Master=:i")
    int check(@Param("i")int i);
}
