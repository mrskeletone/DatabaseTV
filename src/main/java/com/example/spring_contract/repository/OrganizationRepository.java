package com.example.spring_contract.repository;

import com.example.spring_contract.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization,Integer> {
    @Query(nativeQuery = true,value = "select MAX(id_organization) from organization")
    int findMaxId();
    @Query(nativeQuery = true,value = "select * from organization where id_organization=:id")
    List<Organization> findByIds(@Param("id")int id);
    @Query(nativeQuery = true,value = "select * from organization where name like %:name%")
    List<Organization> findByName(@Param("name")String name);
    @Query(nativeQuery = true,value = "select * from organization where address like %:address%")
    List<Organization> findByAddress(@Param("address")String address);
    @Query(nativeQuery = true,value = "select o.* from organization o join mydb.suppliers s on o.id_organization = s.id_organization where Full_name like %:sup%")
    List<Organization> findByManager(@Param("sup")String sup);
}
