package com.example.pjymes.service.productionManage;

import com.example.pjymes.domain.ProductionPlan;
import com.example.pjymes.dto.ProductionPlanDTO;
import com.example.pjymes.dto.SearchDTO;

import java.util.List;

public interface ProductionActivityService {

    //생산중인경우 생산량만 올라감
    ProductionPlanDTO production(ProductionPlanDTO productionPlanDTO);

    List<ProductionPlanDTO> list(SearchDTO searchDTO);

}
