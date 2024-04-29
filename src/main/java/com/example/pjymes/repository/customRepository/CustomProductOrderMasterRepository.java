package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.OrderMaster;
import com.example.pjymes.domain.ProductOrderMaster;
import com.example.pjymes.dto.SearchDTO;

import java.util.List;

public interface CustomProductOrderMasterRepository {

    List<ProductOrderMaster> findByKeyword(SearchDTO searchDTO);

    String getOrderNo(String newOrderNo);

}
