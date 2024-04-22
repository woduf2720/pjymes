package com.example.pjymes.service;

import com.example.pjymes.dto.CommonCodeDTO;
import com.example.pjymes.service.systemManage.CommonCodeService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class CommonCodeServiceTests {

    @Autowired
    private CommonCodeService commonCodeService;

    @Test
    public void testRegister() {
        CommonCodeDTO commonCodeDTO = CommonCodeDTO.builder()
                .majorCode("02")
                .commonCodeName("하위코드1")
                .build();

        log.info("commonCodeDTO : " + commonCodeDTO);
        String menuId = commonCodeService.register(commonCodeDTO);
        log.info("menuId : " + menuId);
    }

    @Test
    public void testModify() {

        CommonCodeDTO commonCodeDTO = CommonCodeDTO.builder()
                .commonCodeId("0103")
                .commonCodeName("복구한 코드명")
                .build();
        log.info("commonCodeDTO : " + commonCodeDTO);
        commonCodeService.modify(commonCodeDTO);
    }

    @Test
    public void testList() {
        List<CommonCodeDTO> responseDTO = commonCodeService.list();
        log.info(responseDTO);
    }
}
