package com.example.pjymes.controller;

import com.example.pjymes.dto.MenuDTO;
import com.example.pjymes.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuRestController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/menu")
    public List<MenuDTO> getMenu() {
        return menuService.list();
    }
}
