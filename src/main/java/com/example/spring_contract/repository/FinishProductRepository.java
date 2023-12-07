package com.example.spring_contract.repository;

import com.example.spring_contract.model.FinishedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinishProductRepository extends JpaRepository<FinishedProduct,Integer> {
    @Query(nativeQuery = true,value = "select * from finishedproduct f where Id_Fiinished_product=:id")
    List<FinishedProduct> findId(@Param("id") int id);

    @Query(nativeQuery = true,value = "select * from finishedproduct f where f.name_product like %:name%")
    List<FinishedProduct> findByProduct(@Param("name") String name);

    @Query(nativeQuery = true,value = "select  f.name_product,f.Id_Fiinished_product,f.Date_Creation,f.Id_Manufucture,f.quantity,f.Id_warehouse" +
            " from finishedproduct f join mydb.manufucture m on m.Id_Manufucture = f.Id_Manufucture where f.Id_Manufucture=:man")
    List<FinishedProduct> findByMan(@Param("man") int man);

    @Query(nativeQuery = true,value = "select DISTINCT  f.quantity,f.Id_Manufucture,f.Date_Creation,f.Id_warehouse,f.Id_Fiinished_product,f.name_product from  groupmaterials " +
            " join mydb.materials m on groupmaterials.Id_Materials = m.Id_Materials " +
            "join mydb.finishedproduct f on f.Id_Fiinished_product = groupmaterials.Id_Fiinished_product " +
            "where name like %:mat%")
    List<FinishedProduct> findByMaterials(@Param("mat") String mat);

    @Query(nativeQuery = true,value = "select  * from finishedproduct where Date_Creation BETWEEN :begin and :end")
    List<FinishedProduct> findByDate(@Param("begin")LocalDate begin,@Param("end") LocalDate end);
    @Query(nativeQuery = true,value = "select MAX(Id_Fiinished_product) from finishedproduct ")
    int findMax();
    @Query(nativeQuery = true,value = "select count(*) from finishedproduct where Id_Fiinished_product=:i")
    int check(@Param("i")int i);
    @Query(nativeQuery = true,value = "SELECT DISTINCT f.* FROM finishedproduct f \n" +
            "JOIN groupmaterials g \n" +
            "JOIN materials m \n" +
            "JOIN suppliers s ON m.Id_suppliers = s.Id_suppliers\n" +
            " ON g.Id_Materials = m.Id_Materials \n" +
            " ON f.Id_Fiinished_product = g.Id_Fiinished_product\n" +
            " WHERE m.Id_suppliers=:id")
    List<FinishedProduct> findAllBySup(@Param("id")int id);
    @Query(nativeQuery = true,value = "SELECT MAX(f.Date_Creation) FROM finishedproduct f WHERE f.Id_warehouse=:warehouse")
    LocalDate findMaxDate(@Param("warehouse")int warehouse);
    @Query(nativeQuery = true,value = "SELECT f.* FROM finishedproduct f JOIN groupmaterials g ON f.Id_Fiinished_product = g.Id_Fiinished_product WHERE g.Id_Materials=:idMat")
    List<FinishedProduct> findAllByMat(@Param("idMat")int idMat  );
    @Query(nativeQuery = true,value = "SELECT distinct f.* FROM finishedproduct f\n" +
            " JOIN groupmaterials g\n" +
            " JOIN materials m\n" +
            " JOIN suppliers s \n" +
            " JOIN organization o ON s.id_organization = o.id_organization ON m.Id_suppliers = s.Id_suppliers ON g.Id_Materials = m.Id_Materials ON f.Id_Fiinished_product = g.Id_Fiinished_product\n" +
            " WHERE o.id_organization=:idOrg")
    List<FinishedProduct> findAllByOrg(@Param("idOrg") int idOrg);
@Query(nativeQuery = true,value = "SELECT TvPrice(f.Id_Fiinished_product) FROM finishedproduct f where Id_Fiinished_product=:id")
    int findPrice(@Param("id")int id);
}
