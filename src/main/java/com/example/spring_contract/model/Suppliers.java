package com.example.spring_contract.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "suppliers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Suppliers {
    @Id
    @Column(name = "Id_suppliers")
    private int id;
    @Column(name = "Full_name")
    private String name;


    @OneToMany(mappedBy ="suppliers" )
    private List<Materials>materials;


    @Override
    public String toString() {
        return "Suppliers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", organization=" + organization +
                '}';
    }

    public Suppliers(int id, String name,  Organization organization) {
        this.id = id;
        this.name = name;
        this.organization = organization;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_organization")
    private Organization organization;
}
