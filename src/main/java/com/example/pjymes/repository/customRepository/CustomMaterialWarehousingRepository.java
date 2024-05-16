package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.Warehousing;
import com.example.pjymes.dto.SearchDTO;

import java.util.List;

public interface CustomMaterialWarehousingRepository {

    List<Warehousing> findByKeyword(SearchDTO searchDTO);


}
