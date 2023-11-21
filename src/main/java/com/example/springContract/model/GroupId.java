package com.example.springContract.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class GroupId implements Serializable {
    private Materials materials;
    private  FinishedProduct finishedProduct;
//    private static final long serialVersionUID = 2702030623316532366L;

}
