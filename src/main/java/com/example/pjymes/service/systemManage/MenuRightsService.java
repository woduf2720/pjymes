package com.example.pjymes.service.systemManage;

import com.example.pjymes.dto.MenuRightsDTO;

import java.util.List;

public interface MenuRightsService {
    Long register(MenuRightsDTO menuRightsDTO);

    void modify(MenuRightsDTO menuRightsDTO);

    List<MenuRightsDTO> list();

    List<MenuRightsDTO> listByUserTypeId(Long userTypeId);
}
