package com.example.pjymes.controller;

import com.example.pjymes.dto.BomDTO;
import com.example.pjymes.dto.ItemDTO;
import com.example.pjymes.service.BomService;
import com.example.pjymes.service.ItemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bomManage")
@Log4j2
public class BomManageRestController {

    @Autowired
    private BomService bomService;

    @GetMapping
    public List<BomDTO> getBom() {
        log.info("getBom...");
        return bomService.list();
    }

}
