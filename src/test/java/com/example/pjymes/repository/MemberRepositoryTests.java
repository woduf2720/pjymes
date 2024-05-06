package com.example.pjymes.repository;

import com.example.pjymes.domain.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMember(){
        IntStream.rangeClosed(1,3).forEach(i -> {
            Member member = Member.builder()
                    .mid("user"+i)
                    .mname("유저"+i)
                    .mpw(passwordEncoder.encode("1111"))
                    .build();



            memberRepository.save(member);
        });
    }

    @Test
    public void testRead(){

        Optional<Member> result = memberRepository.findById("user1");

        Member member = result.orElseThrow();
        log.info(member);

    }
}
