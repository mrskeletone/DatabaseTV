package com.example.spring_contract.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    @Id
    @Column(name = "id_Manager")
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "earned")
    private int earned;
    @Column(name = "salary")
    private int salary;

    @OneToMany(mappedBy = "manager")
    private List<Sell> sells ;

    public Manager(int id, String name, int earned, int salary) {
        this.id = id;
        this.name = name;
        this.earned = earned;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", earned=" + earned +
                ", salary=" + salary +
                '}';
    }
}
