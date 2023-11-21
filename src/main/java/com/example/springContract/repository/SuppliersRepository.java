package com.example.springContract.repository;

import com.example.springContract.model.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Integer> {

    @Query(nativeQuery = true,value = "select * from suppliers where Id_suppliers=:id")
    List<Suppliers> findByIds(@Param("id")int id);
    @Query(nativeQuery = true,value = "select s.Id_suppliers,s.address,s.Full_name,s.id_organization from suppliers s" +
            " join mydb.organization o on o.id_organization = s.id_organization" +
            " where name like %:org%")
    List<Suppliers> findByOrg(@Param("org")String org);

    @Query(nativeQuery = true,value = "select distinct s.Full_name,s.address,s.Id_suppliers,s.id_organization from suppliers s " +
            "join mydb.materials m on s.Id_suppliers = m.Id_suppliers " +
            "where name like %:mat%")
    List<Suppliers> findByMat(@Param("mat") String mat);
}
