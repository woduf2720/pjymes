package com.example.pjymes.controller.systemManage;

import com.example.pjymes.dto.CommonCodeDTO;
import com.example.pjymes.service.systemManage.CommonCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commonCode")
@Log4j2
@RequiredArgsConstructor
public class CommonCodeRestController {

    private final CommonCodeService commonCodeService;

    @GetMapping
    public List<CommonCodeDTO> getCommonCode() {
        log.info("getCommonCode...");
        return commonCodeService.list();
    }

    @PostMapping
    public Map<String, Long> postCommonCode(@RequestBody CommonCodeDTO commonCodeDTO) {
        log.info("postCommonCode..." + commonCodeDTO);
        Long commonCodeId = commonCodeService.register(commonCodeDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("commonCodeId", commonCodeId);
        return resultMap;
    }

    @PutMapping
    public Map<String, Long> putMenu(@RequestBody CommonCodeDTO commonCodeDTO) {
        log.info("put..." + commonCodeDTO);
        commonCodeService.modify(commonCodeDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("id", commonCodeDTO.getId());
        return resultMap;
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deleteMenu(@PathVariable Long id) {
        commonCodeService.remove(id);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("id", id);
        return resultMap;
    }

    @GetMapping("/{id}")
    public List<CommonCodeDTO> getListByParentId(@PathVariable Long id) {
        log.info("getListByParentId...");
        id = id == 0? null : id;
        return commonCodeService.listByParentId(id);
    }
}
