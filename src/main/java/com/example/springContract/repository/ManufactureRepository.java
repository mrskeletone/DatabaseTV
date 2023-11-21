package com.example.springContract.repository;

import com.example.springContract.model.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufactureRepository extends JpaRepository<Manufacture,Integer> {
}
