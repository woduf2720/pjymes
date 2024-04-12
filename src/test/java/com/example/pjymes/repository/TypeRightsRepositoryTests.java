package com.example.pjymes.repository;

import com.example.pjymes.domain.Menu;
import com.example.pjymes.domain.TypeRights;
import com.example.pjymes.domain.TypeRightsId;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class TypeRightsRepositoryTests {

    @Autowired
    private TypeRightsRepository typeRightsRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void testInsert() {
        List<Menu> menuList = menuRepository.findAll();
        menuList.forEach(menu -> {
            TypeRights typeRights = TypeRights.builder()
                    .typeRightsId(new TypeRightsId(1L, menu.getMenuId()))
                    .build();
            typeRightsRepository.save(typeRights);
        });


        log.info("insert test");
    }

    @Test
    public void testSelect() {
        TypeRightsId typeRightsId = new TypeRightsId(1L, 1L);

        Optional<TypeRights> result = typeRightsRepository.findById(typeRightsId);

        TypeRights typeRights = result.orElseThrow();

        log.info(typeRights);
    }

    @Test
    public void testUpdate() {
        TypeRightsId typeRightsId = new TypeRightsId(1L, 1L);

        Optional<TypeRights> result = typeRightsRepository.findById(typeRightsId);

        TypeRights typeRights = result.orElseThrow();

        typeRights.change("systemManage");

        typeRightsRepository.save(typeRights);
    }
}
