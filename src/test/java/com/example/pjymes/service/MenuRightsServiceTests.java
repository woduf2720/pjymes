package com.example.pjymes.service;

import com.example.pjymes.dto.MenuRightsDTO;
import com.example.pjymes.service.systemManage.MenuRightsService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class MenuRightsServiceTests {

    @Autowired
    private MenuRightsService menuRightsService;

    @Test
    public void testRegister() {
        MenuRightsDTO menuRightsDTO = MenuRightsDTO.builder()
                .build();
        log.info("menuRightsDTO : " + menuRightsDTO);

        Long menuRightsId = menuRightsService.register(menuRightsDTO);

        log.info("menuRightsId : " + menuRightsId);
    }

    @Test
    public void testModify() {

        MenuRightsDTO menuRightsDTO = MenuRightsDTO.builder()
                .build();
        menuRightsService.modify(menuRightsDTO);
    }

    @Test
    public void testList() {
        List<MenuRightsDTO> responseDTO = menuRightsService.listByCommonCodeId(1L);
        log.info(responseDTO);
    }
}
