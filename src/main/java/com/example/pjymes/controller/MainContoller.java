package com.example.pjymes.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class MainContoller {

    @GetMapping("/main")
    public void hello(Model model){
        log.info("main.....");
    }
}
