package com.example.pjymes.service.productManage;

import com.example.pjymes.dto.ProductDeliveryDTO;
import com.example.pjymes.dto.ProductOrderMasterDTO;
import com.example.pjymes.dto.ProductOrderSubDTO;
import com.example.pjymes.dto.SearchDTO;

import java.util.List;

public interface ProductDeliveryService {

    ProductOrderMasterDTO register(List<ProductDeliveryDTO> productDeliveryDTOList);

    List<ProductDeliveryDTO> list(SearchDTO searchDTO);
}
