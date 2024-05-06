package com.example.pjymes.controller;

import com.example.pjymes.dto.RoleDTO;
import com.example.pjymes.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/standardManage")
@RequiredArgsConstructor
public class StandardManageController {

    private final RoleService roleService;

    @GetMapping("/memberManage")
    public void memberManage(Model model) {
        log.info("memberManage.....");

        List<RoleDTO> roleDTOList = roleService.list();

        model.addAttribute("roleDTOList", roleDTOList);
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
