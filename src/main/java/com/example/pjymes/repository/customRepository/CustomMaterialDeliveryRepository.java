package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.Delivery;
import com.example.pjymes.dto.SearchDTO;

import java.util.List;

public interface CustomMaterialDeliveryRepository {

    List<Delivery> findByKeyword(SearchDTO searchDTO);
}
