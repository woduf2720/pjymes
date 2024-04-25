package com.example.pjymes.service;

import com.example.pjymes.dto.MenuDTO;
import com.example.pjymes.service.adminMenu.MenuService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class MenuServiceTests {

    @Autowired
    private MenuService menuService;

    @Test
    public void testRegister() {
        MenuDTO menuDTO = MenuDTO.builder()
                .name("테스트메뉴")
                .url("testMenu")
                .parentId(28L)
                .build();
        log.info("menuDTO : " + menuDTO);

        Long menuId = menuService.register(menuDTO);

        log.info("menuId : " + menuId);
    }

    @Test
    public void testModify() {

        MenuDTO menuDTO = MenuDTO.builder()
                .id(2L)
                .name("new menuName2")
                .build();
        menuService.modify(menuDTO);
    }

    @Test
    public void testList() {
        List<MenuDTO> responseDTO1 = menuService.list();
        List<MenuDTO> responseDTO2 = menuService.list();
        log.info(responseDTO1);
        log.info(responseDTO2);
    }
}
