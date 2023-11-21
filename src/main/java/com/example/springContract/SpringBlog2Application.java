package com.example.springContract;

import com.example.springContract.model.FinishedProduct;
import com.example.springContract.model.Materials;
import com.example.springContract.repository.FinishProductRepository;
import com.example.springContract.repository.MaterialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBlog2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBlog2Application.class, args);
    }

}
