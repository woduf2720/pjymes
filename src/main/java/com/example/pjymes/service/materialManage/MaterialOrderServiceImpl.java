package com.example.pjymes.service.materialManage;

import com.example.pjymes.domain.OrderMaster;
import com.example.pjymes.domain.OrderSub;
import com.example.pjymes.dto.OrderMasterDTO;
import com.example.pjymes.dto.OrderSubDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.repository.OrderMasterRepository;
import com.example.pjymes.repository.OrderSubRepository;
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
public class MaterialOrderServiceImpl implements MaterialOrderService {

    private final ModelMapper modelMapper;
    private final OrderMasterRepository orderMasterRepository;
    private final OrderSubRepository orderSubRepository;

    @Override
    @Transactional
    public String register(OrderMasterDTO orderMasterDTO) {
        if(orderMasterDTO.getOrderNo() == null){
            //발주번호 생성
            String newOrderNo = orderMasterDTO.getOrderDate().format(DateTimeFormatter.ofPattern("yyMMdd"))
                    +"-"+orderMasterDTO.getCustomerCode()+"-";
            String getOrderNo = orderMasterRepository.getOrderNo(newOrderNo);
            orderMasterDTO.setOrderNo(newOrderNo + getOrderNo);
        }

        List<OrderSubDTO> orderSubDTOList = orderMasterDTO.getOrderSubDTOList();

        //orderMaster 저장
        log.info("materialOrder register...");
        OrderMaster orderMaster = modelMapper.map(orderMasterDTO, OrderMaster.class);
        orderMasterRepository.save(orderMaster);
        log.info("orderMaster complete...");

        //orderSub 저장
        List<OrderSub> orderSubList = new ArrayList<>();

        orderSubDTOList.forEach(orderSubDTO -> {
            OrderSub orderSub = modelMapper.map(orderSubDTO, OrderSub.class);
            orderSub.setOrderMaster(orderMaster);
            orderSubList.add(orderSub);
        });

        orderSubRepository.saveAll(orderSubList);
        log.info("orderSub complete...");

        return orderMasterDTO.getOrderNo();
    }

    @Override
    public OrderMasterDTO readOne(String orderNo) {
        log.info("orderMaster readOne...");
        OrderMaster orderMaster = orderMasterRepository.findById(orderNo).orElseThrow();

        return modelMapper.map(orderMaster, OrderMasterDTO.class);
    }

    @Override
    public List<OrderMasterDTO> orderMasterList() {
        log.info("orderMaster list...");
        List<OrderMaster> result = orderMasterRepository.findAll();
        log.info(result);
        return result.stream()
                .map(orderMaster -> modelMapper.map(orderMaster, OrderMasterDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderMasterDTO> orderMasterListByKeyword(SearchDTO searchDTO) {
        log.info("orderMasterListByCustomerCode...");
        List<OrderMaster> result = orderMasterRepository.findByKeyword(searchDTO);
        return result.stream()
                .map(orderMaster -> modelMapper.map(orderMaster, OrderMasterDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderSubDTO> orderSubList(String orderNo) {
        return orderSubRepository.findAllByOrderMasterOrderNo(orderNo)
                .stream().map(orderSub -> modelMapper.map(orderSub, OrderSubDTO.class)).toList();
    }

    @Override
    @Transactional
    public void deleteOrderSub(List<OrderSubDTO> orderSubDTOList) {
        String orderNo = orderSubDTOList.get(0).getOrderNo();

        //삭제하고 난 뒤에 master의 금액을 수정 만약 금액이 0이면 마스터도 삭제
        for(OrderSubDTO orderSubDTO : orderSubDTOList) {
            orderSubRepository.deleteById(orderSubDTO.getOrderSubId());
        }

        //남아있는 sub 조회
        List<OrderSub> orderSubList = orderSubRepository.findAllByOrderMasterOrderNo(orderNo);
        Long totalPrice = 0L;
        for (OrderSub orderSub : orderSubList) {
            totalPrice += orderSub.getPrice();
        }

        if(totalPrice == 0L) {
            orderMasterRepository.deleteById(orderNo);
        }else{
            Optional<OrderMaster> result = orderMasterRepository.findById(orderNo);
            OrderMaster orderMaster = result.orElseThrow();
            orderMaster.change(
                    orderMaster.getOrderDate(),
                    orderMaster.getDeliveryDate(),
                    totalPrice
            );
            orderMasterRepository.save(orderMaster);
        }

    }

}
