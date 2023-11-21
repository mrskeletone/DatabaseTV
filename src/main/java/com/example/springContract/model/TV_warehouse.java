package com.example.springContract.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tv_warehouse")
public class TV_warehouse {
    @Id
    @Column(name = "Id_warehouse")
    private int id;
    @Column(name = "last_receipt_date")
    private LocalDate last_receipt_date;
    @ManyToOne()
    @JoinColumn(name = "id_Storekeeper")
    private Storekeeper storekeeper;
    @OneToMany(mappedBy = "tv_warehouse")
    private List<FinishedProduct> finishedProducts;

    @Override
    public String toString() {
        return "TV_warehouse{" +
                "id=" + id +
                ", last_receipt_date=" + last_receipt_date +
                ", storekeeper=" + storekeeper +
                '}';
    }

    public TV_warehouse(int id, LocalDate last_receipt_date, Storekeeper storekeeper) {
        this.id = id;
        this.last_receipt_date = last_receipt_date;
        this.storekeeper = storekeeper;
    }
}
