package com.example.pjymes.service.standardManage;

import com.example.pjymes.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    String register(MemberDTO memberDTO);

    MemberDTO readOne(String mid);

    void modify(MemberDTO memberDTO);

    List<MemberDTO> list();
}
