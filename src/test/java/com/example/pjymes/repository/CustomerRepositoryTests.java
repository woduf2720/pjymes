package com.example.pjymes.repository;

import com.example.pjymes.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Log4j2
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testInsert() {
        Customer customer = Customer.builder()
                .code("C0002")
                .name("회사")
                .address("주소")
                .category("분류")
                .active(true)
                .build();

        Customer result = customerRepository.save(customer);
        log.info("id : " + result);
    }

    @Test
    public void testSelect() {
        String code = "C0001";

        Optional<Customer> result = customerRepository.findById(code);

        Customer customer = result.orElseThrow();

        log.info(customer);
    }

    @Test
    public void testUpdate() {
        String code = "C0002";

        Optional<Customer> result = customerRepository.findById(code);

        Customer customer = result.orElseThrow();

        customer.change("변경된 회사명", "변경된 분류", "","","","","",true);

        customerRepository.save(customer);
    }

    @Test
    public void testDelete() {
        String code = "C0002";

        customerRepository.deleteById(code);
    }

}
