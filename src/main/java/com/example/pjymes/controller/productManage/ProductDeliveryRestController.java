package com.example.pjymes.controller.productManage;

import com.example.pjymes.domain.ProductOrderSub;
import com.example.pjymes.dto.*;
import com.example.pjymes.service.materialManage.MaterialWarehousingService;
import com.example.pjymes.service.productManage.ProductDeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productDelivery")
@Log4j2
@RequiredArgsConstructor
public class ProductDeliveryRestController {

    private final ProductDeliveryService productDeliveryService;

    @GetMapping
    public List<ProductDeliveryDTO> getProductDelivery(SearchDTO searchDTO){
        log.info("getProductDelivery..." + searchDTO);
        return productDeliveryService.list(searchDTO);
    }

    @PostMapping
    public ProductOrderMasterDTO postProductDelivery(@RequestBody List<ProductDeliveryDTO> productDeliveryDTOList) {
        log.info("postProductDelivery..." + productDeliveryDTOList);
        return productDeliveryService.register(productDeliveryDTOList);
    }

}
