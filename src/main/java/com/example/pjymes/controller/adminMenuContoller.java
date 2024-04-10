package com.example.pjymes.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/adminMenu")
public class adminMenuContoller {

    @GetMapping("/menuManage")
    public void hello(Model model){
        log.info("menuManage.....");
    }
}
