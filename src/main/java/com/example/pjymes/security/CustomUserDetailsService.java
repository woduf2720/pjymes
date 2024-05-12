package com.example.pjymes.security;

import com.example.pjymes.domain.Member;
import com.example.pjymes.repository.MemberRepository;
import com.example.pjymes.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException {
        log.info("loadUserByUsername: " + mid);

        //사용지 체크
        Optional<Member> result = memberRepository.findById(mid);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("사용자가 없습니다.");
        }

        MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(result.get());

        log.info("memberDTO");
        log.info(memberSecurityDTO);

        return memberSecurityDTO;
    }
}
