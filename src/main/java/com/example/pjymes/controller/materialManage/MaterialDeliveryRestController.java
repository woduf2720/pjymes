package com.example.pjymes.controller.materialManage;

import com.example.pjymes.dto.DeliveryDTO;
import com.example.pjymes.dto.LotMasterDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.dto.WarehousingDTO;
import com.example.pjymes.service.materialManage.MaterialDeliveryService;
import com.example.pjymes.service.materialManage.MaterialWarehousingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/materialDelivery")
@Log4j2
@RequiredArgsConstructor
public class MaterialDeliveryRestController {

    private final MaterialDeliveryService materialDeliveryService;

    @GetMapping("")
    public List<DeliveryDTO> getMaterialDelivery(@ModelAttribute SearchDTO searchDTO){
        log.info("getMaterialDelivery..." + searchDTO);
        return materialDeliveryService.list(searchDTO);
    }

    @GetMapping("/{code}")
    public List<LotMasterDTO> getLotMaster(@PathVariable String code){
        log.info("getLotMaster..." + code);
        return materialDeliveryService.lotList(code);
    }

    @PostMapping
    public Map<String, Long> postMaterialDelivery(@RequestBody List<DeliveryDTO> deliveryDTOList) {
        log.info("postMaterialDelivery..." + deliveryDTOList);
        Long orderNo = materialDeliveryService.register(deliveryDTOList);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("orderNo", orderNo);
        return resultMap;
    }

}
