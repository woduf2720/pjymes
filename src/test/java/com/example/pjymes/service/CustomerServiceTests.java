package com.example.pjymes.service;

import com.example.pjymes.dto.CommonCodeDTO;
import com.example.pjymes.dto.CustomerDTO;
import com.example.pjymes.service.standardManage.CustomerService;
import com.example.pjymes.service.systemManage.CommonCodeService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class CustomerServiceTests {

    @Autowired
    private CustomerService customerService;

    @Test
    public void testRegister() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .code("C0002")
                .name("회사2")
                .address("주소2")
                .active(true)
                .build();

        String code = customerService.register(customerDTO);
        log.info("code : " + code);
    }

    @Test
    public void testModify() {

        CustomerDTO customerDTO = CustomerDTO.builder()
                .code("C0002")
                .name("회사변경")
                .manager("담당자")
                .active(false)
                .build();
        log.info("customerDTO : " + customerDTO);
//        customerService.modify(customerDTO);
    }

    @Test
    public void testList() {
        List<CustomerDTO> customerDTOList = customerService.list();
        log.info(customerDTOList);
    }
}
