package com.example.pjymes.controller.ProductionManage;

import com.example.pjymes.dto.ProductionPlanDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.service.productionManage.ProductionActivityService;
import com.example.pjymes.service.productionManage.ProductionPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productionActivity")
@Log4j2
@RequiredArgsConstructor
public class ProductionActivityRestController {

    private final ProductionActivityService productionActivityService;

    @GetMapping
    public List<ProductionPlanDTO> getProductionActivity(@ModelAttribute SearchDTO searchDTO) {
        log.info("getProductionActivity...");
        return productionActivityService.list(searchDTO);
    }

    @PutMapping
    public Map<String, String> putProductionActivity(@RequestBody ProductionPlanDTO productionPlanDTO) {
        log.info("putProductionActivity..." + productionPlanDTO);
        productionActivityService.modify(productionPlanDTO);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("planNo", productionPlanDTO.getPlanNo());
        return resultMap;
    }
}
