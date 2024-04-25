package com.example.pjymes.service.systemManage;

import com.example.pjymes.dto.CommonCodeDTO;
import com.example.pjymes.dto.MenuDTO;

import java.util.List;

public interface CommonCodeService {
    Long register(CommonCodeDTO commonCodeDTO);

    CommonCodeDTO readOne(Long commonCodeId);

    void modify(CommonCodeDTO commonCodeDTO);

    void remove(Long commonCodeId);

    List<CommonCodeDTO> list();

    List<CommonCodeDTO> listByParentId(Long id);

}
