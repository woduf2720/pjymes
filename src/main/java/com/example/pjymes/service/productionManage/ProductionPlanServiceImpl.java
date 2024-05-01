package com.example.pjymes.service.productionManage;

import com.example.pjymes.domain.*;
import com.example.pjymes.dto.ProductDeliveryDTO;
import com.example.pjymes.dto.ProductionPlanDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductionPlanServiceImpl implements ProductionPlanService {

    private final ModelMapper modelMapper;
    private final ProductionPlanRepository productionPlanRepository;
    private final ProductOrderSubRepository productOrderSubRepository;

    @Override
    @Transactional
    public String register(ProductionPlanDTO productionPlanDTO) {
        log.info("register..." + productionPlanDTO);
        Long orderSubId = productionPlanDTO.getOrderSubId();

        ProductOrderSub productOrderSub = productOrderSubRepository.findById(orderSubId).orElseThrow();

        ProductionPlan productionPlan = ProductionPlan.builder()
                .planNo(productOrderSub.getProductOrderMaster().getOrderNo() + "-01")
                .productOrderSub(productOrderSub)
                .quantity(productionPlanDTO.getQuantity())
                .orderDate(productionPlanDTO.getOrderDate())
                .dueDate(productionPlanDTO.getDueDate())
                .build();

        return productionPlanRepository.save(productionPlan).getPlanNo();
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
