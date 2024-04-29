package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.Customer;

import java.util.List;

public interface CustomCustomerRepository {

    List<Customer> findByKeyword(String keyword);

}
