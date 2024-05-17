package com.example.pjymes.controller.productManage;

import com.example.pjymes.dto.DeliveryDTO;
import com.example.pjymes.dto.LotMasterDTO;
import com.example.pjymes.dto.ProductWarehousingDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.service.materialManage.MaterialDeliveryService;
import com.example.pjymes.service.productManage.ProductWarehousingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productWarehousing")
@Log4j2
@RequiredArgsConstructor
public class ProductWarehousingRestController {

    private final ProductWarehousingService productWarehousingService;

    @GetMapping
    public List<ProductWarehousingDTO> getMaterialDelivery(SearchDTO searchDTO){
        log.info("getMaterialDelivery..." + searchDTO);
        return productWarehousingService.list(searchDTO);
    }

    @PostMapping
    public int postMaterialDelivery(@RequestBody List<ProductWarehousingDTO> productWarehousingDTOList) {
        log.info("postMaterialDelivery..." + productWarehousingDTOList);
        return productWarehousingService.register(productWarehousingDTOList);
    }

}
