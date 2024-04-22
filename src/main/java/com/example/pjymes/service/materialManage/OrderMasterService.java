package com.example.pjymes.service.materialManage;

import com.example.pjymes.dto.BomDTO;
import com.example.pjymes.dto.OrderMasterDTO;

import java.util.List;

public interface OrderMasterService {
    String register(OrderMasterDTO orderMasterDTO);

    OrderMasterDTO readOne(String orderNo);

    void modify(OrderMasterDTO orderMasterDTO);

    List<OrderMasterDTO> list();
}
