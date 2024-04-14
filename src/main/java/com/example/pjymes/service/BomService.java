package com.example.pjymes.service;

import com.example.pjymes.dto.BomDTO;
import com.example.pjymes.dto.ItemDTO;

import java.util.List;

public interface BomService {
    Long register(BomDTO bomDTO);

    BomDTO readOne(Long bomId);

    void modify(BomDTO bomDTO);

    List<BomDTO> list();
}
