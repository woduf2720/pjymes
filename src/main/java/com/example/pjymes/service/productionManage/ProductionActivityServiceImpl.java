package com.example.pjymes.service.productionManage;

import com.example.pjymes.domain.ProductionPlan;
import com.example.pjymes.dto.ProductionPlanDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.repository.ProductionPlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductionActivityServiceImpl implements ProductionActivityService {

    private final ModelMapper modelMapper;
    private final ProductionPlanRepository productionPlanRepository;

    @Override
    public ProductionPlanDTO production(ProductionPlanDTO productionPlanDTO) {
        //작업지시가 1인 작업(생산중인거) 찾아서 생산량 올림
        List<ProductionPlan> productionPlanList = productionPlanRepository.findByStatus(1);

        //생산중인거는 무조건 한개여야함
        ProductionPlan productionPlan = productionPlanList.get(0);
        log.info("확인 : " + productionPlan.getProductionQuantity());
        log.info("확인 : " + productionPlanDTO.getProductionQuantity());
        Long sumQuantity = productionPlan.getProductionQuantity() + productionPlanDTO.getProductionQuantity();
        productionPlan.changeProductionQuantity(sumQuantity);

        ProductionPlan saveData = productionPlanRepository.save(productionPlan);
        return modelMapper.map(saveData, ProductionPlanDTO.class);
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
