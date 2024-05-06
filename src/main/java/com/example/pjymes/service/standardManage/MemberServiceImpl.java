package com.example.pjymes.service.standardManage;

import com.example.pjymes.domain.Member;
import com.example.pjymes.domain.Role;
import com.example.pjymes.dto.MemberDTO;
import com.example.pjymes.repository.MemberRepository;
import com.example.pjymes.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{

    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(MemberDTO memberDTO) {
        log.info("member register...");
        Member member = modelMapper.map(memberDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberDTO.getMpw()));
        Role role = roleRepository.findById(memberDTO.getRoleId()).orElseThrow();
        member.setRole(role);
        return memberRepository.save(member).getMid();
    }

    @Override
    public MemberDTO readOne(String mid) {
        log.info("member readOne...");
        Optional<Member> result = memberRepository.findById(mid);
        Member member = result.orElseThrow();
        return modelMapper.map(member, MemberDTO.class);
    }

    @Override
    public void modify(MemberDTO memberDTO) {
        log.info("member modify...");
        Optional<Member> result = memberRepository.findById(memberDTO.getMid());
        Member member = result.orElseThrow();
        member.change(memberDTO.getMname(), memberDTO.getActive());
        Role role = roleRepository.findById(memberDTO.getRoleId()).orElseThrow();
        member.setRole(role);
        memberRepository.save(member);
    }

    @Override
    public List<MemberDTO> list() {
        log.info("memberlist...");
        List<Member> result = memberRepository.findAll();
        log.info(result.toString());
        List<MemberDTO> memberDTOList = result.stream()
                .map(member -> modelMapper.map(member, MemberDTO.class)).collect(Collectors.toList());

        log.info("memberDTOList...");
        log.info(memberDTOList);
        return memberDTOList;
    }

    @Override
    public void changePassword(MemberDTO memberDTO) {
        log.info("member changePassword...");
        Optional<Member> result = memberRepository.findById(memberDTO.getMid());
        Member member = result.orElseThrow();
        member.changePassword(passwordEncoder.encode(memberDTO.getMpw()));
        Role role = roleRepository.findById(memberDTO.getRoleId()).orElseThrow();
        member.setRole(role);
        memberRepository.save(member);
    }
}
