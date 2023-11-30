package com.example.spring_contract.repository;

import com.example.spring_contract.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
}
