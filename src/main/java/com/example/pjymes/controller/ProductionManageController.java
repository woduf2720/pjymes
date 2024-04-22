package com.example.pjymes.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/productionManage")
public class ProductionManageController {

    @GetMapping("/productionPlan")
    public void productionPlan(){
        log.info("productionPlan.....");
    }

    @GetMapping("/productionActivity")
    public void productionActivity(){
        log.info("productionActivity.....");
    }

    @GetMapping("/productionRecord")
    public void productionRecord(){
        log.info("productionRecord.....");
    }
}
