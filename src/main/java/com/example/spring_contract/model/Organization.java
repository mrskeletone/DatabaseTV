package com.example.spring_contract.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "organization")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    @Id
    @Column(name = "id_organization")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String addres;

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addres='" + addres + '\'' +
                '}';
    }

    public Organization(int id, String name, String addres) {
        this.id = id;
        this.name = name;
        this.addres = addres;
    }

    @OneToMany(mappedBy ="organization" )
    private List<Suppliers> suppliers;
}
