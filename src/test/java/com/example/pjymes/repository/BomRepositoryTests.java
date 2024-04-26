package com.example.pjymes.repository;

import com.example.pjymes.domain.Bom;
import com.example.pjymes.domain.CommonCode;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BomRepositoryTests {

    @Autowired
    private BomRepository bomRepository;

    @Test
    public void testInsert() {
    }

    @Test
    public void testSelect() {

        List<Bom> bomList = bomRepository.findAll();


        log.info(bomList);
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testDelete() {
    }
}
