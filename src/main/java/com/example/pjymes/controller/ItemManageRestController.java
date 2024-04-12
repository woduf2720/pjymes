package com.example.pjymes.controller;

import com.example.pjymes.dto.ItemDTO;
import com.example.pjymes.dto.MemberDTO;
import com.example.pjymes.service.ItemManageService;
import com.example.pjymes.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itemManage")
@Log4j2
public class ItemManageRestController {

    @Autowired
    private ItemManageService itemManageService;

    @GetMapping
    public List<ItemDTO> getItem() {
        log.info("getItem...");
        return itemManageService.list();
    }

}
