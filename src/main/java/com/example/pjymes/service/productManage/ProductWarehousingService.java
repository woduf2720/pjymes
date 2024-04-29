package com.example.pjymes.service.productManage;

import com.example.pjymes.dto.LotMasterDTO;
import com.example.pjymes.dto.ProductWarehousingDTO;
import com.example.pjymes.dto.SearchDTO;

import java.util.List;

public interface ProductWarehousingService {

    Long register(List<ProductWarehousingDTO> productWarehousingDTOS);

    List<ProductWarehousingDTO> list(SearchDTO searchDTO);


}
