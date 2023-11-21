package com.example.springContract.model;

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

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Organization(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(mappedBy ="organization" )
    private List<Suppliers> suppliers;
}
