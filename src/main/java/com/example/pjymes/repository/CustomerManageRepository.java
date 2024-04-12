package com.example.pjymes.repository;

import com.example.pjymes.domain.Customer;
import com.example.pjymes.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerManageRepository extends JpaRepository<Customer, String>{

}
