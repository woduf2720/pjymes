package com.example.pjymes.controller.materialManage;

import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.dto.WarehousingDTO;
import com.example.pjymes.service.materialManage.MaterialWarehousingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/materialWarehousing")
@Log4j2
@RequiredArgsConstructor
public class MaterialWarehousingRestController {

    private final MaterialWarehousingService materialWarehousingService;

    @GetMapping
    public List<WarehousingDTO> getMaterialWarehousing(SearchDTO searchDTO){
        log.info("getMaterialWarehousing..." + searchDTO);
        return materialWarehousingService.list(searchDTO);
    }

    @PostMapping
    public Map<String, String> postMaterialWarehousing(@RequestBody List<WarehousingDTO> warehousingDTOList) {
        log.info("postMaterialWarehousing..." + warehousingDTOList);
        String orderNo = materialWarehousingService.register(warehousingDTOList);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("orderNo", orderNo);
        return resultMap;
    }

}
