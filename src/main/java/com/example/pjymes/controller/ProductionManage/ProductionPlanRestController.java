package com.example.pjymes.controller.ProductionManage;

import com.example.pjymes.dto.ItemDTO;
import com.example.pjymes.dto.ProductionPlanDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.service.productionManage.ProductionPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productionPlan")
@Log4j2
@RequiredArgsConstructor
public class ProductionPlanRestController {

    private final ProductionPlanService productionPlanService;

    @GetMapping
    public List<ProductionPlanDTO> getProductionPlan(SearchDTO searchDTO) {
        log.info("getProductionPlan...");
        return productionPlanService.list(searchDTO);
    }

    @PostMapping
    public ProductionPlanDTO postProductionPlan(@RequestBody ProductionPlanDTO productionPlanDTO) {
        log.info("postProductionPlan..." + productionPlanDTO);
        return productionPlanService.register(productionPlanDTO);
    }
}
