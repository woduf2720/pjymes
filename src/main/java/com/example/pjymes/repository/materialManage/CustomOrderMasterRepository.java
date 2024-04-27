package com.example.pjymes.repository.materialManage;

import com.example.pjymes.domain.OrderMaster;
import com.example.pjymes.dto.SearchDTO;

import java.util.List;

public interface CustomOrderMasterRepository {

    List<OrderMaster> findByKeyword(SearchDTO searchDTO);

    String getOrderNo(String newOrderNo);

}
