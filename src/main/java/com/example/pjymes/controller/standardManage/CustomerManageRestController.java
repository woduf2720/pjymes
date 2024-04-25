package com.example.pjymes.controller.standardManage;

import com.example.pjymes.dto.CustomerDTO;
import com.example.pjymes.service.standardManage.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping
    public Map<String, String> postCustomer(@RequestBody CustomerDTO customerDTO) {
        log.info("postCustomer..." + customerDTO);
        String customerCode = customerService.register(customerDTO);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("customerCode", customerCode);
        return resultMap;
    }

    @PutMapping
    public Map<String, String> putCustomer(@RequestBody CustomerDTO customerDTO) {
        log.info("put..." + customerDTO);
        customerService.modify(customerDTO);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("customerCode", customerDTO.getCode());
        return resultMap;
    }

    @GetMapping("/{keyword}")
    public List<CustomerDTO> getCustomerSearch(@PathVariable String keyword) {
        log.info("getCustomerSearch...");
        return customerService.listByKeyword(keyword);
    }
}
