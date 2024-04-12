package com.example.pjymes.service;

import com.example.pjymes.dto.ItemDTO;
import com.example.pjymes.dto.MemberDTO;

import java.util.List;

public interface ItemManageService {
    String register(ItemDTO itemDTO);

    ItemDTO readOne(Long itemCode);

    void modify(ItemDTO itemDTO);

    List<ItemDTO> list();
}
