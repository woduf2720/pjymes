package com.example.pjymes.controller.standardManage;

import com.example.pjymes.dto.MemberDTO;
import com.example.pjymes.service.standardManage.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    @GetMapping
    public List<MemberDTO> getMember() {
        log.info("getMember...");
        List<MemberDTO> dtoList = memberService.list();
        log.info(dtoList);
        return dtoList;
    }

    @PostMapping
    public Map<String, String> postMember(@RequestBody MemberDTO memberDTO) {
        log.info("postMember..." + memberDTO);
        String memberId = memberService.register(memberDTO);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("memberId", memberId);
        return resultMap;
    }

    @PutMapping
    public Map<String, String> putMember(@RequestBody MemberDTO memberDTO) {
        log.info("putMember..." + memberDTO);
        memberService.modify(memberDTO);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("memberId", memberDTO.getMid());
        return resultMap;
    }

    @PutMapping("/password")
    public Map<String, String> putPassword(@RequestBody MemberDTO memberDTO) {
        log.info("putPassword..." + memberDTO);
        memberService.changePassword(memberDTO);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("memberId", memberDTO.getMid());
        return resultMap;
    }
}
