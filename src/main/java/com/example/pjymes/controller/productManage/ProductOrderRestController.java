package com.example.pjymes.controller.productManage;

import com.example.pjymes.dto.*;
import com.example.pjymes.service.materialManage.MaterialOrderService;
import com.example.pjymes.service.productManage.ProductOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productOrder")
@Log4j2
@RequiredArgsConstructor
public class ProductOrderRestController {

    private final ProductOrderService productOrderService;

    @GetMapping
    public List<ProductOrderMasterDTO> getProductOrderMaster() {
        log.info("getProductOrderMaster...");
        return productOrderService.productOrderMasterList();
    }

    @GetMapping("/orderMaster")
    public List<ProductOrderMasterDTO> getProductOrderMasterList(SearchDTO searchDTO) {
        log.info("getProductOrderMasterList...");
        log.info(searchDTO);
        return productOrderService.productOrderMasterListByKeyword(searchDTO);
    }

    @GetMapping("/orderSub/{orderNo}")
    public List<ProductOrderSubDTO> getProductOrderSubList(@PathVariable String orderNo) {
        log.info("getProductOrderSubList...");
        return productOrderService.productOrderSubList(orderNo);
    }

    @GetMapping("/orderSub/uncompleted")
    public List<ProductOrderSubDTO> getUncompletedProductOrderSubList() {
        log.info("getUncompletedProductOrderSubList...");
        return productOrderService.uncompletedProductOrderSubList();
    }

    @PostMapping
    public ProductOrderMasterDTO postProductOrderMaster(@RequestBody ProductOrderMasterDTO productOrderMasterDTO) {
        log.info("postProductOrderMaster..." + productOrderMasterDTO);
        return productOrderService.register(productOrderMasterDTO);
    }

    @DeleteMapping
    public Map<String, Long> deleteProductOrder(@RequestBody List<ProductOrderSubDTO> ProductOrderSubDTOList) {
        log.info("deleteProductOrder..." + ProductOrderSubDTOList);
        productOrderService.deleteOrderSub(ProductOrderSubDTOList);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("orderSubId", (long) ProductOrderSubDTOList.size());
        return resultMap;
    }
}
