package com.example.pjymes.service;

import com.example.pjymes.domain.Customer;
import com.example.pjymes.dto.CustomerDTO;
import com.example.pjymes.repository.CustomerManageRepository;
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
public class CustomerManageServiceImpl implements CustomerManageService{

    private final ModelMapper modelMapper;
    private final CustomerManageRepository customerManageRepository;

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
        List<Customer> result = customerManageRepository.findAll();
        List<CustomerDTO> dtoList = result.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
        return dtoList;
    }

}
