package com.example.pjymes.service.standardManage;

import com.example.pjymes.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    String register(ItemDTO itemDTO);

    ItemDTO readOne(String itemCode);

    void modify(ItemDTO itemDTO);

    List<ItemDTO> list();
}
