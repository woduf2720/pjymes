package com.example.pjymes.service.productManage;

import com.example.pjymes.domain.OrderMaster;
import com.example.pjymes.domain.OrderSub;
import com.example.pjymes.domain.ProductOrderMaster;
import com.example.pjymes.domain.ProductOrderSub;
import com.example.pjymes.dto.*;
import com.example.pjymes.repository.ProductOrderMasterRepository;
import com.example.pjymes.repository.ProductOrderSubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService {

    private final ModelMapper modelMapper;
    private final ProductOrderMasterRepository productOrderMasterRepository;
    private final ProductOrderSubRepository productOrderSubRepository;

    @Override
    @Transactional
    public String register(ProductOrderMasterDTO productOrderMasterDTO) {
        if(productOrderMasterDTO.getOrderNo() == null){
            //수주번호 생성
            String newOrderNo = productOrderMasterDTO.getOrderDate().format(DateTimeFormatter.ofPattern("yyMMdd"))
                    +"-"+productOrderMasterDTO.getCustomerCode()+"-";
            String getOrderNo = productOrderMasterRepository.getOrderNo(newOrderNo);
            productOrderMasterDTO.setOrderNo(newOrderNo + getOrderNo);
        }

        List<ProductOrderSubDTO> productOrderSubDTOList = productOrderMasterDTO.getProductOrderSubDTOList();

        //productOrderMaster 저장
        log.info("materialOrder register...");
        ProductOrderMaster productOrderMaster = modelMapper.map(productOrderMasterDTO, ProductOrderMaster.class);
        productOrderMasterRepository.save(productOrderMaster);
        log.info("orderMaster complete...");

        //productOrderSub 저장
        List<ProductOrderSub> productOrderSubList = new ArrayList<>();

        productOrderSubDTOList.forEach(productOrderSubDTO -> {
            ProductOrderSub productOrderSub = modelMapper.map(productOrderSubDTO, ProductOrderSub.class);
            productOrderSub.setProductOrderMaster(productOrderMaster);
            productOrderSubList.add(productOrderSub);
        });

        productOrderSubRepository.saveAll(productOrderSubList);
        log.info("orderSub complete...");

        return productOrderMasterDTO.getOrderNo();
    }

    @Override
    public ProductOrderMasterDTO readOne(String orderNo) {
        log.info("orderMaster readOne...");
        ProductOrderMaster productOrderMaster = productOrderMasterRepository.findById(orderNo).orElseThrow();

        return modelMapper.map(productOrderMaster, ProductOrderMasterDTO.class);
    }

    @Override
    public List<ProductOrderMasterDTO> productOrderMasterList() {
        log.info("orderMaster list...");
        List<ProductOrderMaster> result = productOrderMasterRepository.findAll();
        return result.stream()
                .map(productOrderMaster -> modelMapper.map(productOrderMaster, ProductOrderMasterDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ProductOrderMasterDTO> productOrderMasterListByKeyword(SearchDTO searchDTO) {
        log.info("orderMasterListByCustomerCode...");
        List<ProductOrderMaster> result = productOrderMasterRepository.findByKeyword(searchDTO);
        return result.stream()
                .map(productOrderMaster -> modelMapper.map(productOrderMaster, ProductOrderMasterDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ProductOrderSubDTO> productOrderSubList(String orderNo) {
        return productOrderSubRepository.findAllByProductOrderMasterOrderNo(orderNo)
                .stream().map(productOrderSub -> modelMapper.map(productOrderSub, ProductOrderSubDTO.class)).toList();
    }

    @Override
    @Transactional
    public void deleteOrderSub(List<ProductOrderSubDTO> ProductOrderSubDTOList) {
        String orderNo = ProductOrderSubDTOList.get(0).getOrderNo();

        //삭제하고 난 뒤에 master의 금액을 수정 만약 금액이 0이면 마스터도 삭제
        for(ProductOrderSubDTO ProductOrderSubDTO : ProductOrderSubDTOList) {
            productOrderSubRepository.deleteById(ProductOrderSubDTO.getOrderSubId());
        }

        //남아있는 sub 조회
        List<ProductOrderSub> productOrderSubList = productOrderSubRepository.findAllByProductOrderMasterOrderNo(orderNo);
        Long totalPrice = 0L;
        for (ProductOrderSub productOrderSub : productOrderSubList) {
            totalPrice += productOrderSub.getPrice();
        }

        if(totalPrice == 0L) {
            productOrderMasterRepository.deleteById(orderNo);
        }else{
            Optional<ProductOrderMaster> result = productOrderMasterRepository.findById(orderNo);
            ProductOrderMaster productOrderMaster = result.orElseThrow();
            productOrderMaster.change(
                    productOrderMaster.getOrderDate(),
                    productOrderMaster.getDeliveryDate(),
                    totalPrice
            );
            productOrderMasterRepository.save(productOrderMaster);
        }

    }

}
