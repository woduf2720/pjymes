package com.example.pjymes.security;

import com.example.pjymes.domain.Member;
import com.example.pjymes.security.dto.MemberSecurityDTO;
import com.example.pjymes.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException {
        log.info("loadUserByUsername: " + mid);

        Optional<Member> result = memberRepository.getWithRoles(mid);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("사용자가 없습니다.");
        }

        Member member = result.get();

        MemberSecurityDTO memberSecurityDTO =
                new MemberSecurityDTO(
                        member.getMid(),
                        member.getMpw(),
                        member.getMname(),
                        member.getRoleSet()
                                .stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_"+memberRole.name()))
                                .collect(Collectors.toList())
                );

        log.info("memberDTO");
        log.info(memberSecurityDTO);

        return memberSecurityDTO;
    }
}
