package com.example.pjymes.service.productionManage;

import com.example.pjymes.domain.ProductOrderSub;
import com.example.pjymes.domain.ProductionPlan;
import com.example.pjymes.dto.ProductionPlanDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.repository.ProductOrderSubRepository;
import com.example.pjymes.repository.ProductionPlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductionActivityServiceImpl implements ProductionActivityService {

    private final ModelMapper modelMapper;
    private final ProductionPlanRepository productionPlanRepository;

    @Override
    public void modify(ProductionPlanDTO productionPlanDTO) {
        String planNo = productionPlanDTO.getPlanNo();
        ProductionPlan productionPlan = productionPlanRepository.findById(planNo).orElseThrow();
        LocalDateTime startDate = productionPlanDTO.getStartDate();
        LocalDateTime endDate = productionPlanDTO.getEndDate();
        productionPlan.change(productionPlanDTO.getQuantity(), startDate, endDate);
        productionPlanRepository.save(productionPlan);
    }

    @Override
    public List<ProductionPlanDTO> list(SearchDTO searchDTO) {

        log.info("test list...");
        List<ProductionPlan> result = productionPlanRepository.findAll();
        return result.stream()
                .map(productionPlan -> modelMapper.map(productionPlan, ProductionPlanDTO.class))
                .collect(Collectors.toList());
    }
}
