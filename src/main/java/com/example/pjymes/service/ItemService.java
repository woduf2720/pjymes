package com.example.pjymes.service;

import com.example.pjymes.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    String register(ItemDTO itemDTO);

    ItemDTO readOne(Long itemCode);

    void modify(ItemDTO itemDTO);

    List<ItemDTO> list();
}
