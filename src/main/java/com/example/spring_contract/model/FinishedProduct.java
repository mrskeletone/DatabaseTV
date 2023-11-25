package com.example.spring_contract.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "finishedproduct")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinishedProduct {
    @Id
    @Column(name = "Id_Fiinished_product")
    private int id;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "name_product")
    private String nameProduct;
    @Column(name = "Date_Creation")
    private LocalDate dateCreation;

    @ManyToOne( )
    @JoinColumn(name = "Id_Manufucture")
    private Manufacture manufacture;
    @ManyToOne()
    @JoinColumn(name = "Id_warehouse")
    private TV_warehouse tv_warehouse;

    @OneToMany(mappedBy = "finishedProduct")
    private List<Sell> sells;
    @OneToMany(mappedBy = "finishedProduct")
    private List<GroupMaterials> groupMaterials;

    @Override
    public String toString() {
        return "FinishedProduct{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", nameProduct='" + nameProduct + '\'' +
                ", dateCreation=" + dateCreation +
                ", manufacture=" + manufacture +
                ", tv_warehouse=" + tv_warehouse +
                '}';
    }

    public FinishedProduct(Integer id, Integer quantity, String nameProduct, LocalDate date, Manufacture manufacture, TV_warehouse warehouse) {
        this.id=id;
        this.quantity=quantity;
        this.nameProduct=nameProduct;
        this.dateCreation=date;
        this.manufacture=manufacture;
        this.tv_warehouse=warehouse;
    }
}
