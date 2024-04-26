package com.example.pjymes.controller.standardManage;

import com.example.pjymes.dto.BomDTO;
import com.example.pjymes.dto.CustomerDTO;
import com.example.pjymes.service.standardManage.BomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bomManage")
@Log4j2
@RequiredArgsConstructor
public class BomManageRestController {

    private final BomService bomService;

    @GetMapping("/{itemCode}")
    public List<BomDTO> getBom(@PathVariable String itemCode) {
        log.info("getBom...");
        return bomService.readOne(itemCode);
    }

    @PostMapping
    public Map<String, Long> postBom(@RequestBody BomDTO bomDTO) {
        log.info("postBom..." + bomDTO);
        Long id = bomService.register(bomDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("customerCode", id);
        return resultMap;
    }

    @PutMapping
    public Map<String, Long> putBom(@RequestBody BomDTO bomDTO) {
        log.info("put..." + bomDTO);
        bomService.modify(bomDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("id", bomDTO.getId());
        return resultMap;
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deleteBom(@PathVariable Long id) {
        bomService.remove(id);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("id", id);
        return resultMap;
    }
}
