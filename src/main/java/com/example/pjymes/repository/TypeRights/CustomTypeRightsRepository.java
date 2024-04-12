package com.example.pjymes.repository.TypeRights;

import com.example.pjymes.domain.CommonCode;
import com.example.pjymes.domain.TypeRights;
import com.example.pjymes.dto.CommonCodeDTO;

import java.util.List;

public interface CustomTypeRightsRepository {

    List<TypeRights> listBySubCode(String subCode);

}
