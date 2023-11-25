package com.example.spring_contract.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "master")
@Data

@NoArgsConstructor
@AllArgsConstructor
public class Master {
    @Id
    @Column(name = "id_Master")
    private int id_master;
    @Column(name = "Name")
    private String name;
    @Column(name = "salary")
    private int salary;
    @Column(name = "specializations")
    private String specializations;
    @OneToMany(mappedBy = "master")
    private List<Manufacture> manufacture;

    public Master(int id_master, String name, int salary, String specializations) {
        this.id_master = id_master;
        this.name = name;
        this.salary = salary;
        this.specializations = specializations;
    }

    @Override
    public String toString() {
        return "Master{" +
                "id_master=" + id_master +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", specializations='" + specializations + '\'' +
                '}';
    }
}
