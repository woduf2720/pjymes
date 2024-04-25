package com.example.pjymes.repository;

import com.example.pjymes.domain.Menu;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MenuRepositoryTests {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,3).forEach(i -> {
            Menu menu = Menu.builder()
                    .name("menu"+i)
                    .build();

            Menu result = menuRepository.save(menu);
            log.info("menuId : " + result.getId());
        });
    }

    @Test
    public void testSelect() {
        Long id = 1L;

        Optional<Menu> result = menuRepository.findById(id);

        Menu menu = result.orElseThrow();

        log.info(menu);
    }

    @Test
    public void testUpdate() {
        Long menuId = 4L;

        Optional<Menu> result = menuRepository.findById(menuId);

        Menu menu = result.orElseThrow();

        menu.change(1,"관리자 메뉴", "systemManage");

        menuRepository.save(menu);
    }

    @Test
    public void testDelete() {
        Long menuId = 7L;

        menuRepository.deleteById(menuId);
    }
}
