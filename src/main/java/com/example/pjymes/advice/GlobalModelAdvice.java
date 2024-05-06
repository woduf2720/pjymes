package com.example.pjymes.advice;

import com.example.pjymes.dto.MenuDTO;
import com.example.pjymes.service.adminMenu.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collection;
import java.util.List;

@Log4j2
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAdvice {

    private final MenuService menuService;

    @ModelAttribute("navigationMenu")
    public List<MenuDTO> addMenu() {
        log.info("addMenu toModel");
        return menuService.list();
    }
}
