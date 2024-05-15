package com.example.pjymes.service.standardManage;

import com.example.pjymes.domain.CommonCode;
import com.example.pjymes.domain.Customer;
import com.example.pjymes.dto.CustomerDTO;
import com.example.pjymes.repository.CommonCodeRepository;
import com.example.pjymes.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final CommonCodeRepository commonCodeRepository;

    @Override
    public String register(CustomerDTO customerDTO) {
        log.info("customer register...");
        if(customerDTO.getCode() == null || !customerRepository.existsById(customerDTO.getCode())){
            Customer customer = modelMapper.map(customerDTO, Customer.class);
            return customerRepository.save(customer).getCode();
        }else{
            throw new DataIntegrityViolationException("거래처코드 중복");
        }
    }

    @Override
    public CustomerDTO readOne(String customerCode) {
        log.info("customer readOne...");
        Optional<Customer> result = customerRepository.findById(customerCode);
        Customer customer = result.orElseThrow();
        return modelMapper.map(customer, CustomerDTO.class);
    }

    @Override
    public void modify(CustomerDTO customerDTO) {
        log.info("customer modify...");
        Optional<Customer> result = customerRepository.findById(customerDTO.getCode());
        Customer customer = result.orElseThrow();
        customer.change(customerDTO);
        CommonCode category = commonCodeRepository.findById(customerDTO.getCategoryId()).orElseThrow();
        customer.setCategory(category);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerDTO> list() {
        log.info("customer list...");
        List<Customer> result = customerRepository.findAll();
        return result.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> listByKeyword(String keyword) {
        log.info("listByKeyword...");
        List<Customer> result = customerRepository.findByKeyword(keyword);
        return result.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
    }

}
