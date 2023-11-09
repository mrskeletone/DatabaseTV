package com.example.springblog2.repository;

import com.example.springblog2.model.LoginPassword;
import org.springframework.data.repository.CrudRepository;

public interface LoginPasswordRepository extends CrudRepository<LoginPassword,String> {
}
