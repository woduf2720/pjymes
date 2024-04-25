package com.example.pjymes.repository;

import com.example.pjymes.domain.CommonCode;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class CommonCodeRepositoryTests {

    @Autowired
    private CommonCodeRepository commonCodeRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,3).forEach(i -> {
            CommonCode commonCode = CommonCode.builder()
                    .name("사용자 타입")
                    .description("테스트")
                    .active(true)
                    .build();

            CommonCode result = commonCodeRepository.save(commonCode);
            log.info("id : " + result);
        });
    }

    @Test
    public void testSelect() {
        Long commonCodeId = 1L;

        Optional<CommonCode> result = commonCodeRepository.findById(commonCodeId);

        CommonCode commonCode = result.orElseThrow();

        log.info(commonCode);
    }

    @Test
    public void testUpdate() {
        Long commonCodeId = 1L;

        Optional<CommonCode> result = commonCodeRepository.findById(commonCodeId);

        CommonCode commonCode = result.orElseThrow();

        commonCode.change("변경될 이름", "비고", true);

        commonCodeRepository.save(commonCode);
    }

    @Test
    public void testDelete() {
        Long commonCodeId = 3L;

        commonCodeRepository.deleteById(commonCodeId);
    }
}
