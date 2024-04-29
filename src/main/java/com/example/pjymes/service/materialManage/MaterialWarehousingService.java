package com.example.pjymes.service.materialManage;

import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.dto.WarehousingDTO;

import java.util.List;

public interface MaterialWarehousingService {

    String register(List<WarehousingDTO> warehousingDTOList);

    List<WarehousingDTO> list(SearchDTO searchDTO);
}
