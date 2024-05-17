package com.example.pjymes.repository.customRepository;

import com.example.pjymes.domain.ProductionPlan;
import com.example.pjymes.dto.SearchDTO;

import java.util.List;

public interface CustomProductionPlanRepository {

    List<ProductionPlan> findByKeyword(SearchDTO searchDTO);

    List<ProductionPlan> findCompleteByKeyword(SearchDTO searchDTO);
}
