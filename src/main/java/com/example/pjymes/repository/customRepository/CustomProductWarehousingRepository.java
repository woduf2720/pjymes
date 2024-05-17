package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.ProductWarehousing;
import com.example.pjymes.domain.Warehousing;
import com.example.pjymes.dto.SearchDTO;

import java.util.List;

public interface CustomProductWarehousingRepository {

    List<ProductWarehousing> findByKeyword(SearchDTO searchDTO);


}
