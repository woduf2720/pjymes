package com.example.pjymes.controller.materialManage;

import com.example.pjymes.dto.OrderMasterDTO;
import com.example.pjymes.dto.OrderSubDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.service.materialManage.MaterialOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/materialOrder")
@Log4j2
@RequiredArgsConstructor
public class MaterialOrderRestController {

    private final MaterialOrderService materialOrderService;

    @GetMapping
    public List<OrderMasterDTO> getMaterialOrderMaster() {
        log.info("getMaterialOrderMaster...");
        return materialOrderService.orderMasterList();
    }

    @GetMapping("/orderMaster")
    public List<OrderMasterDTO> getMaterialOrderMasterList(SearchDTO searchDTO) {
        log.info("getMaterialOrderMasterList...");
        log.info(searchDTO);
        return materialOrderService.orderMasterListByKeyword(searchDTO);
    }

    @GetMapping("/orderSub/{orderNo}")
    public List<OrderSubDTO> getMaterialOrderSubList(@PathVariable String orderNo) {
        log.info("getMaterialOrderSubList...");
        return materialOrderService.orderSubList(orderNo);
    }

    @PostMapping
    public OrderMasterDTO postMaterialOrderMaster(@RequestBody OrderMasterDTO orderMasterDTO) {
        log.info("postMaterialOrderMaster..." + orderMasterDTO);
        return materialOrderService.register(orderMasterDTO);
    }

    @DeleteMapping
    public Map<String, Long> deleteMaterialOrder(@RequestBody List<OrderSubDTO> orderSubDTOList) {
        log.info("deleteMaterialOrder..." + orderSubDTOList);
        materialOrderService.deleteOrderSub(orderSubDTOList);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("orderSubId", (long) orderSubDTOList.size());
        return resultMap;
    }
}
