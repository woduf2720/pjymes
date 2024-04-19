package com.example.pjymes.controller;

import com.example.pjymes.dto.CustomerDTO;
import com.example.pjymes.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customerManage")
@Log4j2
@RequiredArgsConstructor
public class CustomerManageRestController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getCustomer() {
        log.info("getCustomer...");
        return customerService.list();
    }

}
