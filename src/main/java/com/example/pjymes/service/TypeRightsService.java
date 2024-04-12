package com.example.pjymes.service;

import com.example.pjymes.domain.TypeRights;
import com.example.pjymes.domain.TypeRightsId;
import com.example.pjymes.dto.MenuDTO;
import com.example.pjymes.dto.TypeRightsDTO;

import java.util.List;

public interface TypeRightsService {
    TypeRightsId register(TypeRightsDTO typeRightsDTO);

    void modify(TypeRightsDTO typeRightsDTO);

    List<TypeRightsDTO> list();

    List<TypeRightsDTO> listBySubCode(String subCode);
}
