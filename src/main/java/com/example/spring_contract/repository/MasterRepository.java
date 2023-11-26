package com.example.spring_contract.repository;

import com.example.spring_contract.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MasterRepository extends JpaRepository<Master,Integer> {
    @Query(nativeQuery = true,value = "select MAX(id_Master) from master ")
    int findMax();
    @Query(nativeQuery = true,value = "select count(*) from master where id_Master=:i")
    int check(@Param("i")int i);
@Query(nativeQuery = true,value = "SELECT m.* FROM master m WHERE m.id_Master NOT IN(SELECT m1.id_Master FROM manufucture m1);")
    List<Master> findMasterWithoutManufacture();
@Query(nativeQuery = true,value = "select * from master where id_Master=:id")
List<Master> findById_master(@Param("id")int id);
@Query(nativeQuery = true,value = "select  * from master where Name like %:name%")
List<Master> findByName(@Param("name")String name);
@Query(nativeQuery = true,value = "select * from master where specializations like %:spec%")
List<Master> findBySpec(@Param("spec")String spec);
@Query(nativeQuery = true,value = "select * from master where salary between :begin AND :end")
List<Master> findBySalary(@Param("begin") int begin,@Param("end")int end);
@Query(nativeQuery = true,value = "select * from master where salary>=:begin")
List<Master> findByBeginSalary(@Param("begin") int begin);
}
