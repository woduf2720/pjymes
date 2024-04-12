package com.example.pjymes.service;

import com.example.pjymes.domain.TypeRightsId;
import com.example.pjymes.dto.MenuDTO;
import com.example.pjymes.dto.TypeRightsDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class TypeRightsServiceTests {

    @Autowired
    private TypeRightsService typeRightsService;

    @Test
    public void testRegister() {
        TypeRightsDTO typeRightsDTO = TypeRightsDTO.builder()
                .typeName("확인")
                .build();
        log.info("typeRightsDTO : " + typeRightsDTO);

        TypeRightsId typeRightsId = typeRightsService.register(typeRightsDTO);

        log.info("typeRightsId : " + typeRightsId);
    }

    @Test
    public void testModify() {

        TypeRightsDTO typeRightsDTO = TypeRightsDTO.builder()
                .typeId(1L)
                .menuId(1L)
                .typeName("확인")
                .build();
        typeRightsService.modify(typeRightsDTO);
    }

    @Test
    public void testList() {
        List<TypeRightsDTO> responseDTO = typeRightsService.list();
        log.info(responseDTO);
    }
}
