package com.example.spring_contract.repository;

import com.example.spring_contract.model.Materials;
import com.example.spring_contract.model.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MaterialsRepository  extends JpaRepository<Materials,Integer> {
    @Query(nativeQuery = true,value = "select MAX(Id_Materials) from materials")
    int findMaxId();
//@Query(nativeQuery = true,value = "INSERT INTO materials (Id_Materials, name, quantity, price, Id_suppliers)\n" +
//        "  VALUES (:id, :name, :quan, :price, :sup);")
//    void saves(@Param("id")int id, @Param("name")String name, @Param("quan")int quan, @Param("price")int price, @Param("sup")Suppliers sup);
}
