package com.example.pjymes.controller.standardManage;

import com.example.pjymes.dto.BomDTO;
import com.example.pjymes.service.BomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bomManage")
@Log4j2
@RequiredArgsConstructor
public class BomManageRestController {

    private final BomService bomService;

    @GetMapping
    public List<BomDTO> getBom() {
        log.info("getBom...");
        return bomService.list();
    }

}
