package com.example.spring_contract.repository;

import com.example.spring_contract.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer> {
    @Query(nativeQuery = true,value = "select MAX(id_Manager) from manager")
    int findMaxId();

    List<Manager> findManagerById(int id);
    @Query(nativeQuery = true,value = "select * from manager where Name like %:name%")
    List<Manager> findManagerByName(@Param("name") String name);

    List<Manager> findManagerBySalaryBetween(int begin,int end);

    List<Manager> findManagerByEarnedBetween(int begin,int end);
    @Query(nativeQuery = true,value = "select * from manager where salary>=:begin")
    List<Manager> findManagerBySalaryBegin(@Param("begin") int begin);
    @Query(nativeQuery = true,value = "select * from manager where manager.earned>=:begin")
    List<Manager> findManagerByEarnedBegin(@Param("begin") int begin);
    @Query(nativeQuery = true,value = "select * from manager where salary<=:end")
    List<Manager> findManagerBySalaryEnd(@Param("end") int end);
    @Query(nativeQuery = true,value = "select * from manager where manager.earned<=:end")
    List<Manager> findManagerByEarnedEnd(@Param("end") int end);
    @Query(nativeQuery = true,value = "CALL Top_Manager()")
    int findTopManager();
}
