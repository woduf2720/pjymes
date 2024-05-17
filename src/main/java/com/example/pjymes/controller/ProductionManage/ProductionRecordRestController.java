package com.example.pjymes.controller.ProductionManage;

import com.example.pjymes.dto.ProductionPlanDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.service.productionManage.ProductionPlanService;
import com.example.pjymes.service.productionManage.ProductionRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productionRecord")
@Log4j2
@RequiredArgsConstructor
public class ProductionRecordRestController {

    private final ProductionRecordService productionRecordService;

    @GetMapping
    public List<ProductionPlanDTO> getProductionRecord(SearchDTO searchDTO) {
        log.info("getProductionRecord...");
        return productionRecordService.list(searchDTO);
    }
}
