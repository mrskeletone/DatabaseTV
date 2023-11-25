package com.example.spring_contract.repository;

import com.example.spring_contract.model.Storekeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StorekeeperRepository extends JpaRepository<Storekeeper,Integer> {
@Query(nativeQuery = true,value = "SELECT s.* FROM storekeeper s WHERE s.id_Storekeeper NOT IN (SELECT tw.id_Storekeeper FROM tv_warehouse tw) ")
    List<Storekeeper> findLessWarehouse();

    @Query(nativeQuery = true,value = "select MAX(id_Storekeeper) from storekeeper ")
    int findMax();
    @Query(nativeQuery = true,value = "select count(*) from storekeeper where id_Storekeeper=:i")
    int check(@Param("i")int i);
    @Query(nativeQuery = true,value = "select * from storekeeper where id_Storekeeper=:id")
    List<Storekeeper> findByIds(@Param("id")int id);
    @Query(nativeQuery = true,value = "select * from storekeeper where Name like %:name%")
    List<Storekeeper> findByName(@Param("name") String name);
    @Query(nativeQuery = true,value = "select * from storekeeper where Salary between :begin AND :end")
    List<Storekeeper> findBetweenSalary(@Param("begin") int begin ,@Param("end") int end);
    @Query(nativeQuery = true,value = "select * from storekeeper where Salary>=:begin")
    List<Storekeeper> findByMinSalary(@Param("begin")int begin);
}
