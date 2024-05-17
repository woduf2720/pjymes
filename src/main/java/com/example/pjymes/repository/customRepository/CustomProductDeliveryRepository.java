package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.ProductDelivery;
import com.example.pjymes.domain.Warehousing;
import com.example.pjymes.dto.SearchDTO;

import java.util.List;

public interface CustomProductDeliveryRepository {

    List<ProductDelivery> findByKeyword(SearchDTO searchDTO);


}
