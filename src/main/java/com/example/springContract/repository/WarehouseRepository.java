package com.example.springContract.repository;

import com.example.springContract.model.TV_warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<TV_warehouse,Integer> {
@Query(nativeQuery = true,value = "select tv.Id_warehouse,tv.id_Storekeeper,tv.last_receipt_date from mydb.tv_warehouse tv join mydb.storekeeper s on s.id_Storekeeper = tv.id_Storekeeper where Name like %:name%")
        List<TV_warehouse> findByName(@Param("name") String name);

@Query(nativeQuery = true,value = "select * from tv_warehouse tv where Id_warehouse=:id")
        List<TV_warehouse> findBysId(@Param("id") int id);

@Query(nativeQuery = true,value = "select DISTINCT tv.id_Storekeeper,  tv.Id_warehouse,tv.last_receipt_date from tv_warehouse tv join mydb.finishedproduct f on tv.Id_warehouse = f.Id_warehouse where name_product like %:product%")
        List<TV_warehouse> findByProduct(@Param("product") String product);
}
