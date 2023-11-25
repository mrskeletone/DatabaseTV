package com.example.spring_contract.repository;

import com.example.spring_contract.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master,Integer> {
}
