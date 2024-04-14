package com.example.pjymes.controller;

import com.example.pjymes.dto.MenuRightsDTO;
import com.example.pjymes.service.MenuRightsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menuRights")
@Log4j2
public class MenuRightsRestController {

    @Autowired
    private MenuRightsService menuRightsService;

    @GetMapping
    public List<MenuRightsDTO> getMenuRights() {
        log.info("getMenuRights...");
        return menuRightsService.list();
    }
    @GetMapping("/{subCode}")
    public List<MenuRightsDTO> getMenuRightsBySubCode(@PathVariable String subCode) {
        log.info("getMenuRightsBySubCode...");
        return menuRightsService.listBySubCode(subCode);
    }

}
