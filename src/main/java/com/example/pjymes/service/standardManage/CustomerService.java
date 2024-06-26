package com.example.pjymes.service.standardManage;

import com.example.pjymes.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    String register(CustomerDTO customerDTO);

    CustomerDTO readOne(String customerCode);

    void modify(CustomerDTO customerDTO);

    List<CustomerDTO> list();

    List<CustomerDTO> listByKeyword(String keyword);
}
