package com.example.pjymes.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/materialManage")
public class MaterialManageController {

    @GetMapping("/materialOrder")
    public void materialOrder(){
        log.info("materialOrder.....");
    }

    @GetMapping("/materialWarehousing")
    public void materialWarehousing(){
        log.info("materialWarehousing.....");
    }

    @GetMapping("/materialDelivery")
    public void materialDelivery(){
        log.info("materialDelivery.....");
    }

    @GetMapping("/materialWarehousingRecord")
    public void materialWarehousingRecord(){
        log.info("materialWarehousingRecord.....");
    }

    @GetMapping("/materialDeliveryRecord")
    public void materialDeliveryRecord(){
        log.info("materialDeliveryRecord.....");
    }
}
