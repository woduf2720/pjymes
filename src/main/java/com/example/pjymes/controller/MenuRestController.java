package com.example.pjymes.controller;

import com.example.pjymes.domain.Menu;
import com.example.pjymes.dto.MenuDTO;
import com.example.pjymes.service.MenuService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
@Log4j2
public class MenuRestController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<MenuDTO> getMenu() {
        return menuService.list();
    }

    @PutMapping("/{menuId}")
    public Map<String, Long> putMenu(@PathVariable("menuId") Long menuId, @RequestBody MenuDTO menuDTO) {
        log.info("put..." + menuDTO);
        menuDTO.setMenuId(menuId);
        menuService.modify(menuDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("menuId", menuId);
        return resultMap;
    }

    @DeleteMapping("/{menuId}")
    public Map<String, Long> deleteMenu(@PathVariable("menuId") Long menuId) {
        menuService.remove(menuId);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("menuId", menuId);
        return resultMap;
    }
}
