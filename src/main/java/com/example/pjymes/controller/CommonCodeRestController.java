package com.example.pjymes.controller;

import com.example.pjymes.dto.CommonCodeDTO;
import com.example.pjymes.dto.MenuDTO;
import com.example.pjymes.service.CommonCodeService;
import com.example.pjymes.service.MenuService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commonCode")
@Log4j2
public class CommonCodeRestController {

    @Autowired
    private CommonCodeService commonCodeService;

    @GetMapping
    public List<CommonCodeDTO> getCommonCode() {
        log.info("getCommonCode...");
        return commonCodeService.list();
    }

    @PostMapping
    public Map<String, String> postCommonCode(@RequestBody CommonCodeDTO commonCodeDTO) {
        log.info("postCommonCode..." + commonCodeDTO);
        String commonCodeId = commonCodeService.register(commonCodeDTO);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("commonCodeId", commonCodeId);
        return resultMap;
    }

    @PutMapping("/{commonCodeId}")
    public Map<String, String> putMenu(@PathVariable("commonCodeId") String commonCodeId, @RequestBody CommonCodeDTO commonCodeDTO) {
        log.info("put..." + commonCodeDTO);
        commonCodeDTO.setCommonCodeId(commonCodeId);
        commonCodeService.modify(commonCodeDTO);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("commonCodeId", commonCodeId);
        return resultMap;
    }

    @DeleteMapping("/{commonCodeId}")
    public Map<String, String> deleteMenu(@PathVariable("commonCodeId") String commonCodeId) {
        commonCodeService.remove(commonCodeId);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("commonCodeId", commonCodeId);
        return resultMap;
    }

    @GetMapping("/major")
    public List<CommonCodeDTO> getMajorCode() {
        log.info("getMajorCode...");
        return commonCodeService.majorCodelist();
    }

    @GetMapping("/sub")
    public List<CommonCodeDTO> getSubCode(CommonCodeDTO commonCodeDTO) {
        log.info("getSubCode...");
        return commonCodeService.subCodelist(commonCodeDTO);
    }
}
