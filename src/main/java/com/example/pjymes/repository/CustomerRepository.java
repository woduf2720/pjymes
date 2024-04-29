package com.example.pjymes.repository;

import com.example.pjymes.domain.Customer;
import com.example.pjymes.repository.customRepository.CustomCustomerRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String>, CustomCustomerRepository {

}
