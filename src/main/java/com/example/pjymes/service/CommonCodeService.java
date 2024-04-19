package com.example.pjymes.service;

import com.example.pjymes.dto.CommonCodeDTO;
import com.example.pjymes.dto.MenuDTO;

import java.util.List;

public interface CommonCodeService {
    String register(CommonCodeDTO commonCodeDTO);

    CommonCodeDTO readOne(String commonCodeId);

    void modify(CommonCodeDTO commonCodeDTO);

    void remove(String commonCodeId);

    List<CommonCodeDTO> list();

    List<CommonCodeDTO> majorCodeList();

    List<CommonCodeDTO> subCodeList(String mojorCode);
}
