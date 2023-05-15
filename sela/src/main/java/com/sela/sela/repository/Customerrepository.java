package com.sela.sela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sela.sela.model.Customer;

@Repository
public interface Customerrepository extends JpaRepository<Customer, Long>{
    Customer findByEmail(String email);
}
