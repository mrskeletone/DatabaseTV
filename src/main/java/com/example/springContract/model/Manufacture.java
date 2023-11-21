package com.example.springContract.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "manufucture")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Manufacture {
    @Id
    @Column(name = "Id_Manufucture")
    private  int id_Manufucture;
    @ManyToOne()
    @JoinColumn(name = "id_Master")
    private Master master;

    @OneToMany(mappedBy = "manufacture")
    private List<FinishedProduct>finishedProducts;

    public Manufacture(int id_Manufucture, Master master) {
        this.id_Manufucture = id_Manufucture;
        this.master = master;
    }

    @Override
    public String toString() {
        return "Manufacture{" +
                "id_Manufucture=" + id_Manufucture +
                ", master=" + master +
                '}';
    }
}
