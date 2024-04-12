package com.example.pjymes.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/adminMenu")
public class AdminMenuContoller {

    @GetMapping("/menuManage")
    public void menuManage(){
        log.info("menuManage.....");
    }
}
