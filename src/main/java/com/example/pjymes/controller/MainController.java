package com.example.pjymes.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class MainController {

    @GetMapping("/main")
    public void main(){
        log.info("main.....");
    }

    @GetMapping("/login")
    public void login(String error, String logout) {
        log.info("login get..........");
        log.info("logout: " + logout);
    }
}
