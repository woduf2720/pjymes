package com.example.pjymes.service;

import com.example.pjymes.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    String register(MemberDTO memberDTO);

    MemberDTO readOne(Long menuId);

    void modify(MemberDTO memberDTO);

    List<MemberDTO> list();
}
