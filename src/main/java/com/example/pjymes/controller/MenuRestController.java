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
        log.info("getMenu...");
        return menuService.list();
    }

    @PostMapping
    public Map<String, Long> postMenu(@RequestParam Long parentId, @RequestBody MenuDTO menuDTO) {
        log.info("postMenu..." + menuDTO);
        menuDTO.setParent(menuService.readOne(parentId));
        Long menuId = menuService.register(menuDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("menuId", menuId);
        return resultMap;
    }

    @PutMapping("/{menuId}")
    public Map<String, Long> putMenu(@PathVariable("menuId") Long menuId, @RequestParam Long parentId, @RequestBody MenuDTO menuDTO) {
        log.info("put..." + menuDTO);
        menuDTO.setMenuId(menuId);
        menuDTO.setParent(menuService.readOne(parentId));
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
