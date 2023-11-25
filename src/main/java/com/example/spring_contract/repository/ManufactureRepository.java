package com.example.spring_contract.repository;

import com.example.spring_contract.model.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufactureRepository extends JpaRepository<Manufacture,Integer> {
}
