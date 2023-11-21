package com.example.springContract.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "storekeeper")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storekeeper {
    @Id
    @Column(name = "id_Storekeeper")
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Salary")
    private int salary;
    @OneToMany(mappedBy = "storekeeper")
    private List<TV_warehouse> tvWarehouses;

    public Storekeeper(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Storekeeper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
