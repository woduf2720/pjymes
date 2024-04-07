package com.example.pjymes.service;

import com.example.pjymes.dto.MenuDTO;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface MenuService {
    Long register(MenuDTO menuDTO);

    MenuDTO readOne(Long menuId);

    void modify(MenuDTO menuDTO);

    void remove(Long menuId);

    List<MenuDTO> list();
}
