package com.example.pjymes.repository;

import com.example.pjymes.domain.Member;
import com.example.pjymes.domain.MemberRole;
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

            member.addRole(MemberRole.USER);

            if(i>= 2){
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        });
    }

    @Test
    public void testRead(){

        Optional<Member> result = memberRepository.getWithRoles("user3");

        Member member = result.orElseThrow();

        log.info(member);
        log.info(member.getRoleSet());

        member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));
    }
}
