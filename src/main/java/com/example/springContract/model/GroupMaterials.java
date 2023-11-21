package com.example.springContract.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Table(name = "groupmaterials")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(GroupId.class)
public class GroupMaterials implements Serializable {
//    private static final long serialVersionUID = -909206262878526790L;

    @Override
    public String toString() {
        return "GroupMaterials{" +
                "finishedProduct=" + finishedProduct +
                ", materials=" + materials +
                '}';
    }

    @Id
    @ManyToOne()
    @JoinColumn(name = "Id_Fiinished_product")
    private FinishedProduct finishedProduct;
    @Id
    @ManyToOne()
    @JoinColumn(name = "Id_Materials")
    private Materials materials;
}
