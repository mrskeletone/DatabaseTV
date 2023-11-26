package com.example.spring_contract.repository;

import com.example.spring_contract.model.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManufactureRepository extends JpaRepository<Manufacture,Integer> {
    @Query(nativeQuery = true,value = "select MAX(Id_Manufucture) from manufucture ")
    int findMax();
    @Query(nativeQuery = true,value = "select count(*) from manufucture where Id_Manufucture=:i")
    int check(@Param("i")int i);
    @Query(nativeQuery = true,value = "select * from manufucture where Id_Manufucture=:id")
    List<Manufacture> findByIds(@Param("id")int id);
    @Query(nativeQuery = true,value = "select m.* from manufucture m join mydb.master m2 on m2.id_Master = m.id_Master where Name like %:master%")
    List<Manufacture> findByMaster(@Param("master") String master);

    @Query(nativeQuery = true, value = "select m.* from manufucture m join mydb.finishedproduct f on m.Id_Manufucture = f.Id_Manufucture where name_product like %:product%")
    List<Manufacture> findByProduct(@Param("product")String product);


}
