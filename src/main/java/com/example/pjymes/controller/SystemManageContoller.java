package com.example.pjymes.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/systemManage")
public class SystemManageContoller {

    @GetMapping("/menuRightsManage")
    public void menuRightsManage(){
        log.info(" menuRightsManage.....");
    }

    @GetMapping("/commonCodeManage")
    public void commonCodeManage(){
        log.info(" commonCodeManage.....");
    }
}
