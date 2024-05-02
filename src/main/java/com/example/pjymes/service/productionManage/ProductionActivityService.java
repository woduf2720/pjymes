package com.example.pjymes.service.productionManage;

import com.example.pjymes.domain.ProductionPlan;
import com.example.pjymes.dto.ProductionPlanDTO;
import com.example.pjymes.dto.SearchDTO;

import java.util.List;

public interface ProductionActivityService {

    void modify(ProductionPlanDTO productionPlanDTO);

    List<ProductionPlanDTO> list(SearchDTO searchDTO);
}
