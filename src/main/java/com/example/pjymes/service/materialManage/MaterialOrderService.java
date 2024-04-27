package com.example.pjymes.service.materialManage;

import com.example.pjymes.dto.OrderMasterDTO;
import com.example.pjymes.dto.OrderSubDTO;
import com.example.pjymes.dto.SearchDTO;

import java.util.List;

public interface MaterialOrderService {
    String register(OrderMasterDTO orderMasterDTO);

    OrderMasterDTO readOne(String orderNo);

    List<OrderMasterDTO> orderMasterList();

    List<OrderMasterDTO> orderMasterListByKeyword(SearchDTO searchDTO);

    List<OrderSubDTO> orderSubList(String orderNo);

    void deleteOrderSub(List<OrderSubDTO> orderSubDTOList);

}
