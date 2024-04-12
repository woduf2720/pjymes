package com.example.pjymes.controller;

import com.example.pjymes.dto.CustomerDTO;
import com.example.pjymes.dto.ItemDTO;
import com.example.pjymes.service.CustomerManageService;
import com.example.pjymes.service.CustomerManageServiceImpl;
import com.example.pjymes.service.ItemManageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customerManage")
@Log4j2
public class CustomerManageRestController {

    @Autowired
    private CustomerManageService customerManageService;

    @GetMapping
    public List<CustomerDTO> getCustomer() {
        log.info("getCustomer...");
        return customerManageService.list();
    }

}
