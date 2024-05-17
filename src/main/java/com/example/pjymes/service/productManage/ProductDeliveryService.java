package com.example.pjymes.service.productManage;

import com.example.pjymes.domain.ProductOrderSub;
import com.example.pjymes.dto.ProductDeliveryDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.dto.WarehousingDTO;

import java.util.List;

public interface ProductDeliveryService {

    ProductOrderSub register(List<ProductDeliveryDTO> productDeliveryDTOList);

    List<ProductDeliveryDTO> list(SearchDTO searchDTO);
}
