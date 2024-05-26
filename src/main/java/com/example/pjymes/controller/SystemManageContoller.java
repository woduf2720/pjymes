package com.example.pjymes.controller;

import com.example.pjymes.dto.RoleDTO;
import com.example.pjymes.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/systemManage")
@RequiredArgsConstructor
public class SystemManageContoller {

    private final RoleService roleService;

    @GetMapping("/menuManage")
    public void menuManage(Model model){
        log.info("menuManage.....");
        List<RoleDTO> roleDTOList = roleService.list();

        model.addAttribute("roleDTOList", roleDTOList);
    }
    @GetMapping("/menuRightsManage")
    public void menuRightsManage(){
        log.info(" menuRightsManage.....");
    }

    @GetMapping("/commonCodeManage")
    public void commonCodeManage(){
        log.info(" commonCodeManage.....");
    }
}
