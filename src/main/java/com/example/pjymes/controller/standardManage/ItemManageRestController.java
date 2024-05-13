package com.example.pjymes.controller.standardManage;

import com.example.pjymes.dto.ItemDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.service.standardManage.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/itemManage")
@Log4j2
@RequiredArgsConstructor
public class ItemManageRestController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemDTO> getItem(SearchDTO searchDTO) {
        log.info("getItem...");
        log.info(searchDTO);
        if(searchDTO.getCategoryId() != null) {
            //분류로만 검색
            return itemService.listByCategory(searchDTO.getCategoryId());
        }else{
            //전체 리스트
            return itemService.list();
        }
    }

    @PostMapping
    public Map<String, String> postItem(@RequestBody ItemDTO itemDTO) {
        log.info("postItem..." + itemDTO);
        String itemCode = itemService.register(itemDTO);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("itemCode", itemCode);
        return resultMap;
    }

    @PutMapping
    public Map<String, String> putItem(@RequestBody ItemDTO itemDTO) {
        log.info("put..." + itemDTO);
        itemService.modify(itemDTO);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("itemCode", itemDTO.getCode());
        return resultMap;
    }
}
