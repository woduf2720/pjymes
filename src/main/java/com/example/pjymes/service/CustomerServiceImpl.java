package com.example.pjymes.service;

import com.example.pjymes.domain.Customer;
import com.example.pjymes.dto.CustomerDTO;
import com.example.pjymes.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    @Override
    public String register(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public CustomerDTO readOne(String customerCode) {
        return null;
    }

    @Override
    public void modify(CustomerDTO customerDTO) {

    }

    @Override
    public List<CustomerDTO> list() {
        log.info("itemlist...");
        List<Customer> result = customerRepository.findAll();
        List<CustomerDTO> dtoList = result.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
        return dtoList;
    }

}
