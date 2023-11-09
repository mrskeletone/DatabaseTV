package com.example.springblog2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "DateSell")
    private LocalDate DateSell;
@Column(name = "price")
    private int price;
@Column(name = "quantity")
    private int quantity;

}
