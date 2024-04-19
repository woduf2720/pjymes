package com.example.pjymes.controller;

import com.example.pjymes.dto.ItemDTO;
import com.example.pjymes.dto.MemberDTO;
import com.example.pjymes.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ItemDTO> getItem() {
        log.info("getItem...");
        return itemService.list();
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
        resultMap.put("itemCode", itemDTO.getItemCode());
        return resultMap;
    }
}
