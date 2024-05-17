package com.example.pjymes.repository;

import com.example.pjymes.domain.ProductOrderSub;
import com.example.pjymes.domain.ProductionPlan;
import com.example.pjymes.repository.customRepository.CustomProductionPlanRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionPlanRepository extends JpaRepository<ProductionPlan, String>, CustomProductionPlanRepository {
    List<ProductionPlan> findByPlanNoContaining(String productOrderNo);
}
