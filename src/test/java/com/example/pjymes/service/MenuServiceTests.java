package com.example.pjymes.service;

import com.example.pjymes.domain.Menu;
import com.example.pjymes.dto.MenuDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import java.util.List;

@SpringBootTest
@Log4j2
public class MenuServiceTests {

    @Autowired
    private MenuService menuService;

    @Test
    public void testRegister() {
        log.info(menuService.getClass().getName());
        MenuDTO parentMenu = menuService.readOne(1L);
        MenuDTO menuDTO = MenuDTO.builder()
                .menuName("new menu7")
                .build();
        log.info("menuDTO : " + menuDTO);

        Long menuId = menuService.register(menuDTO);

        log.info("menuId : " + menuId);
    }

    @Test
    public void testModify() {

        MenuDTO menuDTO = MenuDTO.builder()
                .menuId(2L)
                .menuName("new menuName2")
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
