package com.example.pjymes.repository.customer;

import com.example.pjymes.domain.Customer;

import java.util.List;

public interface CustomCustomerRepository {

    List<Customer> findByKeyword(String keyword);

}
