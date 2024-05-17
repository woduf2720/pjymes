package com.example.pjymes.service.productManage;

import com.example.pjymes.dto.*;

import java.util.List;

public interface ProductOrderService {
    ProductOrderMasterDTO register(ProductOrderMasterDTO productOrderMasterDTO);

    ProductOrderMasterDTO readOne(String orderNo);

    List<ProductOrderMasterDTO> productOrderMasterList();

    List<ProductOrderMasterDTO> productOrderMasterListByKeyword(SearchDTO searchDTO);

    List<ProductOrderSubDTO> productOrderSubList(String orderNo);

    void deleteOrderSub(List<ProductOrderSubDTO> productOrderSubDTOList);

}
