package com.example.spring_contract.repository;

import com.example.spring_contract.model.Materials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialsRepository  extends JpaRepository<Materials,Integer> {
}
