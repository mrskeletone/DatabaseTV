package com.example.springContract.repository;

import com.example.springContract.model.Materials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialsRepository  extends JpaRepository<Materials,Integer> {
}
