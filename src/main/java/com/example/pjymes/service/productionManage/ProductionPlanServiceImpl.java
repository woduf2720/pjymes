package com.example.pjymes.service.productionManage;

import com.example.pjymes.domain.*;
import com.example.pjymes.dto.ProductDeliveryDTO;
import com.example.pjymes.dto.ProductWarehousingDTO;
import com.example.pjymes.dto.ProductionPlanDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.repository.*;
import com.example.pjymes.service.productManage.ProductWarehousingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductionPlanServiceImpl implements ProductionPlanService {

    private final ModelMapper modelMapper;
    private final ProductWarehousingService productWarehousingService;
    private final ProductionPlanRepository productionPlanRepository;
    private final ProductOrderSubRepository productOrderSubRepository;

    @Override
    @Transactional
    public ProductionPlanDTO register(ProductionPlanDTO productionPlanDTO) {
        log.info("register..." + productionPlanDTO);
        Long orderSubId = productionPlanDTO.getOrderSubId();

        ProductOrderSub productOrderSub = productOrderSubRepository.findById(orderSubId).orElseThrow();
        List<ProductionPlan> productionPlanList = productionPlanRepository.findByPlanNoContaining(productOrderSub.getProductOrderMaster().getOrderNo());

        ProductionPlan productionPlan = ProductionPlan.builder()
                .planNo(productOrderSub.getProductOrderMaster().getOrderNo() + "-" + String.format("%02d", productionPlanList.size()+1))
                .productOrderSub(productOrderSub)
                .orderQuantity(productionPlanDTO.getOrderQuantity())
                .orderDate(productionPlanDTO.getOrderDate())
                .dueDate(productionPlanDTO.getDueDate())
                .build();
        ProductionPlan savePlanData = productionPlanRepository.save(productionPlan);
        return modelMapper.map(savePlanData, ProductionPlanDTO.class);
    }

    @Override
    @Transactional
    public ProductionPlanDTO modify(ProductionPlanDTO productionPlanDTO) {
        String planNo = productionPlanDTO.getPlanNo();
        ProductionPlan productionPlan = productionPlanRepository.findById(planNo).orElseThrow();
        String productionItem = productionPlan.getProductOrderSub().getItem().getCode();
        Long productionQuantity = productionPlan.getProductionQuantity();

        if(productionPlan.getStatus() == 0){
            //작업 시작해야되는 경우
            productionPlan.changeStartDate(LocalDateTime.now());
            productionPlan.changeStatus(1);
        }else if(productionPlan.getStatus() == 1){
            //작업 완료해야되는 경우
            productionPlan.changeEndDate(LocalDateTime.now());
            productionPlan.changeStatus(2);

            ProductWarehousingDTO productWarehousingDTO = ProductWarehousingDTO.builder()
                    .itemCode(productionItem)
                    .quantity(productionQuantity)
                    .build();
            List<ProductWarehousingDTO> ProductWarehousingDTOList = new ArrayList<>();
            ProductWarehousingDTOList.add(productWarehousingDTO);
            productWarehousingService.register(ProductWarehousingDTOList);
        }

        ProductionPlan saveData = productionPlanRepository.save(productionPlan);
        return modelMapper.map(saveData, ProductionPlanDTO.class);
    }

    @Override
    public List<ProductionPlanDTO> list(SearchDTO searchDTO) {
        log.info("list...");
        List<ProductionPlan> result = productionPlanRepository.findByKeyword(searchDTO);
        return result.stream()
                .map(productionPlan -> modelMapper.map(productionPlan, ProductionPlanDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductionPlanDTO> listByStatus(int status) {
        log.info("listByStatus...");
        List<ProductionPlan> result = productionPlanRepository.findByStatus(status);
        return result.stream()
                .map(productionPlan -> modelMapper.map(productionPlan, ProductionPlanDTO.class))
                .collect(Collectors.toList());
    }
}
