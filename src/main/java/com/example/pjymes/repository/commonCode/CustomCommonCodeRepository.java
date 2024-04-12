package com.example.pjymes.repository.commonCode;

import com.example.pjymes.domain.CommonCode;
import com.example.pjymes.dto.CommonCodeDTO;

import java.util.List;

public interface CustomCommonCodeRepository {

    List<CommonCode> majorCodeSearch();

    List<CommonCode> subCodeSearch(CommonCodeDTO commonCodeDTO);

    Long majorCodeCountSearch(CommonCodeDTO commonCodeDTO);

    Long subCodeCountSearch(CommonCodeDTO commonCodeDTO);
}
