package com.example.pjymes.service.productionManage;

import com.example.pjymes.dto.ProductionPlanDTO;
import com.example.pjymes.dto.SearchDTO;

import java.util.List;

public interface ProductionPlanService {

    ProductionPlanDTO register(ProductionPlanDTO productionPlanDTO);

    ProductionPlanDTO modify(ProductionPlanDTO productionPlanDTO);

    List<ProductionPlanDTO> list(SearchDTO searchDTO);

    List<ProductionPlanDTO> listByStatus(int status);
}
