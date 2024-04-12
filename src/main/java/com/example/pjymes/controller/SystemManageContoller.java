package com.example.pjymes.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/systemManage")
public class SystemManageContoller {

    @GetMapping("/authorityManage")
    public void authorityManage(){
        log.info(" authorityManage.....");
    }

    @GetMapping("/commonCodeManage")
    public void commonCodeManage(){
        log.info(" commonCodeManage.....");
    }
}
