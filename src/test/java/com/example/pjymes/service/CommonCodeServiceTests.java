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
                .name("사용자 타입2")
                .description("테스트2")
                .parentId(1L)
                .active(true)
                .build();

        log.info("commonCodeDTO : " + commonCodeDTO);
        Long commonCodeId = commonCodeService.register(commonCodeDTO);
        log.info("commonCodeId : " + commonCodeId);
    }

    @Test
    public void testModify() {

        CommonCodeDTO commonCodeDTO = CommonCodeDTO.builder()
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
