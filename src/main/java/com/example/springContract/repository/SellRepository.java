package com.example.springContract.repository;

import com.example.springContract.model.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellRepository extends JpaRepository<Sell,Integer> {
    @Query(nativeQuery = true,value = "select * from sell where IDSell=:id")
    List<Sell> findByIds(@Param("id")int id);
    @Query(nativeQuery = true,value = "select s.IDSell,s.quantity,s.Date_sell,s.id_client,s.Id_finished_product,s.id_manager,s.price from sell s" +
            " join mydb.manager m on m.id_Manager = s.id_manager  where Name like %:man%")
    List<Sell> findByMan(@Param("man")String man);

    @Query(nativeQuery = true,value = "select s.IDSell,s.quantity,s.Date_sell,s.id_client,s.Id_finished_product,s.id_manager,s.price from sell s " +
            " join mydb.client c on c.Id_client = s.id_client" +
            " where name like %:cl%")
    List<Sell> findByCl(@Param("cl") String cl);
    @Query(nativeQuery = true,value = "select * from sell where price between :begin and :end")
    List<Sell> findByPrice(@Param("begin") int begin,@Param("end")int end);
    @Query(nativeQuery = true,value = "select * from sell where price>=:begin")
    List<Sell> findByBeginPrice(@Param("begin") int begin);
@Query(nativeQuery = true,value = "select s.IDSell,s.quantity,s.Date_sell,s.id_client,s.Id_finished_product,s.id_manager,s.price from sell s" +
        " join mydb.finishedproduct f on f.Id_Fiinished_product = s.Id_finished_product" +
        " where name_product like %:product%")
    List<Sell> findByProduct(@Param("product") String product);
}
