package com.example.pjymes.controller.systemManage;

import com.example.pjymes.dto.MenuRightsDTO;
import com.example.pjymes.service.systemManage.MenuRightsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menuRights")
@Log4j2
@RequiredArgsConstructor
public class MenuRightsRestController {

    private final MenuRightsService menuRightsService;

    @GetMapping
    public List<MenuRightsDTO> getMenuRights() {
        log.info("getMenuRights...");
        return menuRightsService.list();
    }
    @GetMapping("/{commonCodeId}")
    public List<MenuRightsDTO> getMenuRightsByCommonCodeId(@PathVariable String commonCodeId) {
        log.info("getMenuRightsByCommonCodeId...");
        return menuRightsService.listByCommonCodeId(commonCodeId);
    }

}
