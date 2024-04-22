package com.example.pjymes.controller.materialManage;

import com.example.pjymes.dto.BomDTO;
import com.example.pjymes.dto.ItemDTO;
import com.example.pjymes.dto.OrderMasterDTO;
import com.example.pjymes.service.BomService;
import com.example.pjymes.service.materialManage.OrderMasterService;
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

    private final OrderMasterService orderMasterService;

    @GetMapping
    public List<OrderMasterDTO> getMaterialOrderMaster() {
        log.info("getMaterialOrderMaster...");
        return orderMasterService.list();
    }

    @PostMapping
    public Map<String, String> postMaterialOrderMaster(@RequestBody OrderMasterDTO orderMasterDTO) {
        log.info("postMaterialOrderMaster..." + orderMasterDTO);
        String orderNo = orderMasterService.register(orderMasterDTO);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("orderNo", orderNo);
        return resultMap;
    }

    @PutMapping
    public Map<String, String> putMaterialOrderMaster(@RequestBody OrderMasterDTO orderMasterDTO) {
        log.info("put..." + orderMasterDTO);
        orderMasterService.modify(orderMasterDTO);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("orderNo", orderMasterDTO.getOrderNo());
        return resultMap;
    }
}
