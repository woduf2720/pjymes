package com.example.pjymes.service.productManage;

import com.example.pjymes.domain.*;
import com.example.pjymes.dto.ProductDeliveryDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.dto.WarehousingDTO;
import com.example.pjymes.repository.LotMasterRepository;
import com.example.pjymes.repository.ProductDeliveryRepository;
import com.example.pjymes.repository.ProductOrderMasterRepository;
import com.example.pjymes.repository.ProductOrderSubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductDeliveryServiceImpl implements ProductDeliveryService {

    private final ModelMapper modelMapper;
    private final ProductOrderMasterRepository productOrderMasterRepository;
    private final ProductOrderSubRepository productOrderSubRepository;
    private final ProductDeliveryRepository productDeliveryRepository;
    private final LotMasterRepository lotMasterRepository;

    @Override
    @Transactional
    public String register(List<ProductDeliveryDTO> productDeliveryDTOList) {
        log.info("register..." + productDeliveryDTOList);
        String orderNo = productDeliveryDTOList.get(0).getOrderNo();

        for(ProductDeliveryDTO productDeliveryDTO : productDeliveryDTOList) {
            log.info("register..." + productDeliveryDTO);
            //입고 저장할때 작동해야되는 기능
            //productOrderSub 입고수량 변경
            ProductOrderSub productOrderSub = productOrderSubRepository.findById(productDeliveryDTO.getId()).orElseThrow();

            productOrderSub.change(productDeliveryDTO.getQuantity());
            productOrderSubRepository.save(productOrderSub);
            log.info("sub저장 : " + orderNo);
            ProductOrderMaster productOrderMaster = productOrderMasterRepository.findById(orderNo).orElseThrow();
            //productOrderMaster sub입고 전부 다됬으면 완료처리
            Long difference  = productOrderSubRepository.getQuantityMinusWarehousingQuantityByOrderNo(orderNo);
            if(difference == 0){
                productOrderMaster.changeActive(true);
                productOrderMaster = productOrderMasterRepository.save(productOrderMaster);
            }
            String lotNo = productDeliveryDTO.getLotNo();
            log.info("master저장 : " + lotNo);
            //lotMaster 수량 변경
            LotMaster lotMaster = lotMasterRepository.findById(lotNo).orElseThrow();
            lotMaster.change(lotMaster.getQuantity() - productDeliveryDTO.getQuantity());
            lotMaster = lotMasterRepository.save(lotMaster);
            log.info("lot저장");

            //delivery 저장
            ProductDelivery productDelivery = modelMapper.map(productDeliveryDTO, ProductDelivery.class);
            productDelivery.setLotMaster(lotMaster);
            productDelivery.setProductOrderMaster(productOrderMaster);
            productDeliveryRepository.save(productDelivery);
            log.info("productDelivery저장");
        }

        return orderNo;
    }

    @Override
    public List<ProductDeliveryDTO> list(SearchDTO searchDTO) {

        log.info("test list...");
        List<ProductDelivery> result = productDeliveryRepository.findAll();
        return result.stream()
                .map(productDelivery -> modelMapper.map(productDelivery, ProductDeliveryDTO.class))
                .collect(Collectors.toList());
    }
}
