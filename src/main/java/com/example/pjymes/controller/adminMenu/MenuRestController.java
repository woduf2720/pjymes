package com.example.pjymes.controller.adminMenu;

import com.example.pjymes.dto.MenuDTO;
import com.example.pjymes.service.adminMenu.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
@Log4j2
@RequiredArgsConstructor
public class MenuRestController {

    private final MenuService menuService;

    @GetMapping
    public List<MenuDTO> getMenu() {
        log.info("getMenu...");
        return menuService.list();
    }

    @PostMapping
    public Map<String, Long> postMenu(@RequestBody MenuDTO menuDTO) {
        log.info("postMenu..." + menuDTO);
//        if(menuDTO.getParentMenuId() != null){
//            menuDTO.setParentMenu(menuService.readOne(menuDTO.getParentMenuId()));
//        }
        Long menuId = menuService.register(menuDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("menuId", menuId);
        return resultMap;
    }

    @PutMapping
    public Map<String, Long> putMenu(@RequestBody MenuDTO menuDTO) {
        log.info("put..." + menuDTO);
        menuService.modify(menuDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("id", menuDTO.getId());
        return resultMap;
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deleteMenu(@PathVariable Long id) {
        menuService.remove(id);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("id", id);
        return resultMap;
    }
}
