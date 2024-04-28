package com.app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.project.repository.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    
}
