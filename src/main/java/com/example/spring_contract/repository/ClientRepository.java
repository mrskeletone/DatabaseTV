package com.example.spring_contract.repository;

import com.example.spring_contract.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Integer> {
@Query(nativeQuery = true,value = "select MAX(Id_client) from client ;")
    int findMaxId();
    @Query(nativeQuery = true,value = "select MIN(Id_client) from client ;")
    int findMinId();
@Query(nativeQuery = true,value = "select * from client where addres like %:address%")
List<Client> findByAddres(@Param("address") String address);
@Query(nativeQuery = true,value = "select * from client where Id_client=:id")
List<Client> findClientsById_client(@Param("id") int id);
    @Query(nativeQuery = true,value = "select * from client where organization like %:org%")
List<Client> findClientsByOrganization(@Param("org") String org);
    @Query(nativeQuery = true,value = "select * from client where name like %:name%")
List<Client> findClientsByName(@Param("name") String name);
}
