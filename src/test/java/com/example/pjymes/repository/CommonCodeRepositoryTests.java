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
                    .commonCodeId("010"+i)
                    .majorCode("01")
                    .subCode("0"+i)
                    .commonCodeName("코드명"+i)
                    .build();

            CommonCode result = commonCodeRepository.save(commonCode);
            log.info("menuId : " + result.getCommonCodeId());
        });
    }

    @Test
    public void testSelect() {
        String commonCodeId = "0103";

        Optional<CommonCode> result = commonCodeRepository.findById(commonCodeId);

        CommonCode commonCode = result.orElseThrow();

        log.info(commonCode);
    }

    @Test
    public void testUpdate() {
        String commonCodeId = "0103";

        Optional<CommonCode> result = commonCodeRepository.findById(commonCodeId);

        CommonCode commonCode = result.orElseThrow();

        commonCode.change("변경된 코드명");

        commonCodeRepository.save(commonCode);
    }

    @Test
    public void testDelete() {
        String commonCodeId = "0103";

        commonCodeRepository.deleteById(commonCodeId);
    }
}
