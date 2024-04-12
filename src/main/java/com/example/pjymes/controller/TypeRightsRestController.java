package com.example.pjymes.controller;

import com.example.pjymes.dto.MenuDTO;
import com.example.pjymes.dto.TypeRightsDTO;
import com.example.pjymes.service.MenuService;
import com.example.pjymes.service.TypeRightsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/typeRights")
@Log4j2
public class TypeRightsRestController {

    @Autowired
    private TypeRightsService typeRightsService;

    @GetMapping
    public List<TypeRightsDTO> getTypeRights() {
        log.info("getTypeRights...");
        return typeRightsService.list();
    }
    @GetMapping("/{subCode}")
    public List<TypeRightsDTO> getTypeRightsBySubCode(@PathVariable String subCode) {
        log.info("getTypeRightsBySubCode...");
        return typeRightsService.listBySubCode(subCode);
    }

}
