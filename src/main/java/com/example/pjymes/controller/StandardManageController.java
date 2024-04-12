package com.example.pjymes.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/standardManage")
public class StandardManageController {

    @GetMapping("/memberManage")
    public void memberManage(){
        log.info("memberManage.....");
    }

    @GetMapping("/itemManage")
    public void itemManage(){
        log.info("itemManage.....");
    }

    @GetMapping("/customerManage")
    public void customerManage(){
        log.info("customerManage.....");
    }

    @GetMapping("/bomManage")
    public void bomManage(){
        log.info("bomManage.....");
    }
}
