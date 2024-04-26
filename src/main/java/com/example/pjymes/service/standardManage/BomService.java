package com.example.pjymes.service.standardManage;

import com.example.pjymes.dto.BomDTO;

import java.util.List;

public interface BomService {
    Long register(BomDTO bomDTO);

    List<BomDTO> readOne(String itemCode);

    void modify(BomDTO bomDTO);

    void remove(Long id);

    List<BomDTO> list();
}
