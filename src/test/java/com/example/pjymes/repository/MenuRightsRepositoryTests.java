package com.example.pjymes.repository;

import com.example.pjymes.domain.Menu;
import com.example.pjymes.domain.MenuRights;
import com.example.pjymes.domain.MenuRightsId;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class MenuRightsRepositoryTests {

    @Autowired
    private MenuRightsRepository menuRightsRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void testInsert() {
        List<Menu> menuList = menuRepository.findAll();
        menuList.forEach(menu -> {
            MenuRights menuRights = MenuRights.builder()
                    .menuRightsId(new MenuRightsId("1", menu.getMenuId()))
                    .build();
            menuRightsRepository.save(menuRights);
        });


        log.info("insert test");
    }

    @Test
    public void testSelect() {
        MenuRightsId menuRightsId = new MenuRightsId("0101", 1L);

        Optional<MenuRights> result = menuRightsRepository.findById(menuRightsId);

        MenuRights menuRights = result.orElseThrow();

        log.info(menuRights);
    }

    @Test
    public void testUpdate() {
        MenuRightsId menuRightsId = new MenuRightsId("1", 1L);

        Optional<MenuRights> result = menuRightsRepository.findById(menuRightsId);

        MenuRights menuRights = result.orElseThrow();

        menuRightsRepository.save(menuRights);
    }

    @Test
    public void testList() {
        List<MenuRights> result = menuRightsRepository.listByCommonCodeId("0101");
        log.info(result.stream().toList());
    }
}
