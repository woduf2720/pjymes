package com.example.pjymes.service.materialManage;

import com.example.pjymes.domain.Bom;
import com.example.pjymes.domain.Customer;
import com.example.pjymes.domain.OrderMaster;
import com.example.pjymes.dto.BomDTO;
import com.example.pjymes.dto.CustomerDTO;
import com.example.pjymes.dto.OrderMasterDTO;
import com.example.pjymes.repository.BomRepository;
import com.example.pjymes.repository.OrderMasterRepository;
import com.example.pjymes.service.BomService;
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
@Transactional
public class OrderMasterServiceImpl implements OrderMasterService {

    private final ModelMapper modelMapper;
    private final OrderMasterRepository orderMasterRepository;

    @Override
    public String register(OrderMasterDTO orderMasterDTO) {
        log.info("orderMaster register...");
        OrderMaster orderMaster = modelMapper.map(orderMasterDTO, OrderMaster.class);
        return orderMasterRepository.save(orderMaster).getOrderNo();
    }

    @Override
    public OrderMasterDTO readOne(String orderNo) {
        log.info("orderMaster readOne...");
        Optional<OrderMaster> result = orderMasterRepository.findById(orderNo);
        OrderMaster orderMaster = result.orElseThrow();
        return modelMapper.map(orderMaster, OrderMasterDTO.class);
    }

    @Override
    public void modify(OrderMasterDTO orderMasterDTO) {
        log.info("orderMaster modify...");
        Optional<OrderMaster> result = orderMasterRepository.findById(orderMasterDTO.getOrderNo());
        OrderMaster orderMaster = result.orElseThrow();
        orderMaster.change(orderMasterDTO);
        orderMasterRepository.save(orderMaster);
    }

    @Override
    public List<OrderMasterDTO> list() {
        log.info("orderMaster list...");
        List<OrderMaster> result = orderMasterRepository.findAll();
        List<OrderMasterDTO> dtoList = result.stream()
                .map(orderMaster -> modelMapper.map(orderMaster, OrderMasterDTO.class)).collect(Collectors.toList());
        return dtoList;
    }
}
