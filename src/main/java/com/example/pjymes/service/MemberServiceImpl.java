package com.example.pjymes.service;

import com.example.pjymes.domain.Member;
import com.example.pjymes.dto.MemberDTO;
import com.example.pjymes.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
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

    @Override
    public String register(MemberDTO memberDTO) {
        log.info("member register...");
        Member member = modelMapper.map(memberDTO, Member.class);
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
        member.change(member.getMname(), "", member.isUseStatus());
        memberRepository.save(member);
    }

    @Override
    public List<MemberDTO> list() {
        log.info("memberlist...");
        List<Member> result = memberRepository.findAll();
        return result.stream()
                .map(member -> modelMapper.map(member, MemberDTO.class)).collect(Collectors.toList());
    }
}
