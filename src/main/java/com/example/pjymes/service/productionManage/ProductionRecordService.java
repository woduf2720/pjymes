package com.example.pjymes.service.productionManage;

import com.example.pjymes.dto.ProductionPlanDTO;
import com.example.pjymes.dto.SearchDTO;

import java.util.List;

public interface ProductionRecordService {

    List<ProductionPlanDTO> list(SearchDTO searchDTO);
}
