package com.example.pjymes.controller;

import com.example.pjymes.dto.CommonCodeDTO;
import com.example.pjymes.dto.RoleDTO;
import com.example.pjymes.service.RoleService;
import com.example.pjymes.service.systemManage.CommonCodeService;
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
    private final CommonCodeService commonCodeService;

    @GetMapping("/memberManage")
    public void memberManage(Model model) {
        log.info("memberManage.....");

        List<RoleDTO> roleDTOList = roleService.list();

        model.addAttribute("roleDTOList", roleDTOList);
    }

    @GetMapping("/itemManage")
    public void itemManage(Model model){
        log.info("itemManage.....");
        //품목분류 가져옴
        List<CommonCodeDTO> categoryList = commonCodeService.listByParentId(1L);
        model.addAttribute("categoryList", categoryList);
    }

    @GetMapping("/customerManage")
    public void customerManage(Model model){
        log.info("customerManage.....");
        //거래처분류 가져옴
        List<CommonCodeDTO> categoryList = commonCodeService.listByParentId(2L);
        model.addAttribute("categoryList", categoryList);
    }

    @GetMapping("/bomManage")
    public void bomManage(Model model){
        log.info("bomManage.....");
        //품목분류 가져옴
        List<CommonCodeDTO> categoryList = commonCodeService.listByParentId(1L);
        model.addAttribute("categoryList", categoryList);
    }
}
