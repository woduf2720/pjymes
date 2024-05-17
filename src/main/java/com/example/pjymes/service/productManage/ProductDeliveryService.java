package com.example.pjymes.service.productManage;

import com.example.pjymes.dto.ProductDeliveryDTO;
import com.example.pjymes.dto.ProductOrderSubDTO;
import com.example.pjymes.dto.SearchDTO;

import java.util.List;

public interface ProductDeliveryService {

    ProductOrderSubDTO register(List<ProductDeliveryDTO> productDeliveryDTOList);

    List<ProductDeliveryDTO> list(SearchDTO searchDTO);
}
