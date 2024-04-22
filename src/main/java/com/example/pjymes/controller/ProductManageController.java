package com.example.pjymes.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/productManage")
public class ProductManageController {

    @GetMapping("/productOrder")
    public void productOrder(){
        log.info("productOrder.....");
    }

    @GetMapping("/productWarehousing")
    public void productWarehousing(){
        log.info("productWarehousing.....");
    }

    @GetMapping("/productDelivery")
    public void productDelivery(){
        log.info("productDelivery.....");
    }

    @GetMapping("/productWarehousingRecord")
    public void productWarehousingRecord(){
        log.info("productWarehousingRecord.....");
    }

    @GetMapping("/productDeliveryRecord")
    public void productDeliveryRecord(){
        log.info("productDeliveryRecord.....");
    }
}
