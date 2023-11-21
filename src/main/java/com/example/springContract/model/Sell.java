package com.example.springContract.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="sell" )
public class Sell {
    @Id
    @Column(name = "IDSell")
    private  int IDSell;
    @Column(name = "Date_sell")
    private LocalDate DateSell;
@Column(name = "price")
    private int price;
@Column(name = "quantity")
    private int quantity;

    @ManyToOne()
    @JoinColumn(name = "id_client")
    private Client client;
    @ManyToOne()
    @JoinColumn(name = "id_manager")
    private Manager manager;
    @ManyToOne()
    @JoinColumn(name = "Id_finished_product")
    private FinishedProduct finishedProduct;



    @Override
    public String toString() {
        return "Sell{" +
                "IDSell=" + IDSell +
                ", DateSell=" + DateSell +
                ", price=" + price +
                ", quantity=" + quantity +
                ", client=" + client +
                ", manager=" + manager +
                ", finishedProduct=" + finishedProduct +
                '}';
    }
}
