package com.example.pjymes.controller;

import com.example.pjymes.dto.MemberDTO;
import com.example.pjymes.dto.MenuDTO;
import com.example.pjymes.service.MemberService;
import com.example.pjymes.service.MenuService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
@Log4j2
public class MemberRestController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<MemberDTO> getMember() {
        log.info("getMember...");
        return memberService.list();
    }

}
