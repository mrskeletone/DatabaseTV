package com.example.springContract.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "materials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materials {
    @Id
    @Column(name = "Id_Materials")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private int price;

    @OneToMany(mappedBy = "materials")
    private List<GroupMaterials> groupMaterials;

    @ManyToOne()
    @JoinColumn(name = "Id_suppliers")
    private Suppliers suppliers;

    public Materials(int id, String name, int quantity, int price, Suppliers suppliers) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.suppliers = suppliers;
    }

    @Override
    public String toString() {
        return "Materials{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", suppliers=" + suppliers +
                '}';
    }
}
