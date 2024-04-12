package com.example.pjymes.service;

import com.example.pjymes.domain.*;
import com.example.pjymes.dto.CommonCodeDTO;
import com.example.pjymes.dto.MemberDTO;
import com.example.pjymes.dto.MenuDTO;
import com.example.pjymes.repository.MemberRepository;
import com.example.pjymes.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        return null;
    }

    @Override
    public MemberDTO readOne(Long menuId) {
        return null;
    }

    @Override
    public void modify(MemberDTO memberDTO) {

    }

    @Override
    public List<MemberDTO> list() {
        log.info("memberlist...");
        List<Member> result = memberRepository.findAll();
        List<MemberDTO> dtoList = result.stream()
                .map(member -> modelMapper.map(member, MemberDTO.class)).collect(Collectors.toList());
        return dtoList;
    }

}
