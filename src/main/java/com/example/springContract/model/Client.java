package com.example.springContract.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @Column(name = "Id_client")
    private int Id_client;
@Column(name = "name")
    private String name;
    @Column(name = "addres")
    private String addres;
    @Column(name = "organization")
    private String organization;

    @OneToMany(mappedBy = "client")
    private List<Sell> sells;

    public Client(int id_client, String name, String addres, String organization) {
        Id_client = id_client;
        this.name = name;
        this.addres = addres;
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "Client{" +
                "Id_client=" + Id_client +
                ", name='" + name + '\'' +
                ", addres='" + addres + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }
}
