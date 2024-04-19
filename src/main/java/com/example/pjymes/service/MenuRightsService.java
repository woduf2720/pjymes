package com.example.pjymes.service;

import com.example.pjymes.domain.MenuRightsId;
import com.example.pjymes.dto.MenuRightsDTO;

import java.util.List;

public interface MenuRightsService {
    MenuRightsId register(MenuRightsDTO menuRightsDTO);

    void modify(MenuRightsDTO menuRightsDTO);

    List<MenuRightsDTO> list();

    List<MenuRightsDTO> listByCommonCodeId(String commonCodeId);
}
