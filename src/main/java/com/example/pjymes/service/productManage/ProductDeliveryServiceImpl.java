package com.example.pjymes.service.productManage;

import com.example.pjymes.domain.*;
import com.example.pjymes.dto.*;
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
    public ProductOrderSubDTO register(List<ProductDeliveryDTO> productDeliveryDTOList) {
        log.info("register..." + productDeliveryDTOList);
        String orderNo = productDeliveryDTOList.get(0).getOrderNo();
        Long orderSubId = productDeliveryDTOList.get(0).getOrderSubId();
        Long sumQuantity = 0L;
        ProductOrderMaster productOrderMaster = productOrderMasterRepository.findById(orderNo).orElseThrow();

        for(ProductDeliveryDTO productDeliveryDTO : productDeliveryDTOList) {
            sumQuantity += productDeliveryDTO.getQuantity();
            log.info("register..." + productDeliveryDTO);
            //입고 저장할때 작동해야되는 기능
            String lotNo = productDeliveryDTO.getLotNo();
            //lotMaster 수량 변경
            LotMaster lotMaster = lotMasterRepository.findById(lotNo).orElseThrow();
            lotMaster.change(lotMaster.getQuantity() - productDeliveryDTO.getQuantity());
            lotMaster = lotMasterRepository.save(lotMaster);
            log.info("lot저장 : " + lotNo);

            //delivery 저장
            ProductDelivery productDelivery = modelMapper.map(productDeliveryDTO, ProductDelivery.class);
            productDelivery.setLotMaster(lotMaster);
            productDelivery.setProductOrderMaster(productOrderMaster);
            productDeliveryRepository.save(productDelivery);
            log.info("productDelivery저장");
        }
        //productOrderSub 출고수량 변경
        ProductOrderSub productOrderSub = productOrderSubRepository.findById(orderSubId).orElseThrow();

        productOrderSub.change(sumQuantity);
        ProductOrderSub saveSubData = productOrderSubRepository.save(productOrderSub);
        ProductOrderSubDTO saveSubDTOData = modelMapper.map(saveSubData, ProductOrderSubDTO.class);
        log.info("sub저장 : " + saveSubDTOData);

        //productOrderMaster sub입고 전부 다됬으면 완료처리
        OrderStatus orderStatus = productOrderSubRepository.getQuantityMinusWarehousingQuantityByOrderNo(orderNo);
        productOrderMaster.changeOrderStatus(orderStatus);
        productOrderMasterRepository.save(productOrderMaster);
        log.info("master저장");
        return saveSubDTOData;
    }

    @Override
    public List<ProductDeliveryDTO> list(SearchDTO searchDTO) {
        log.info("list...");
        List<ProductDelivery> result = productDeliveryRepository.findByKeyword(searchDTO);
        return result.stream()
                .map(productDelivery -> modelMapper.map(productDelivery, ProductDeliveryDTO.class))
                .collect(Collectors.toList());
    }
}
