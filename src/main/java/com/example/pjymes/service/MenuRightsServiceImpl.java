package com.example.pjymes.service;

import com.example.pjymes.domain.MenuRights;
import com.example.pjymes.domain.MenuRightsId;
import com.example.pjymes.dto.MenuRightsDTO;
import com.example.pjymes.repository.MenuRightsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MenuRightsServiceImpl implements MenuRightsService{

    private final ModelMapper modelMapper;
    private final MenuRightsRepository menuRightsRepository;

    @Override
    public MenuRightsId register(MenuRightsDTO menuRightsDTO) {
        log.info("typeRights register...");
        MenuRights menuRights = modelMapper.map(menuRightsDTO, MenuRights.class);
        MenuRightsId menuRightsId = menuRightsRepository.save(menuRights).getMenuRightsId();
        return menuRightsId;
    }

    @Override
    public void modify(MenuRightsDTO menuRightsDTO) {

    }

    @Override
    public List<MenuRightsDTO> list() {
        log.info("menuRights list...");
        List<MenuRights> result = menuRightsRepository.findAll();
        List<MenuRightsDTO> dtoList = result.stream()
                .map(menuRights -> modelMapper.map(menuRights, MenuRightsDTO.class)).collect(Collectors.toList());

        log.info(dtoList);
        return dtoList;
    }

    @Override
    public List<MenuRightsDTO> listByCommonCodeId(String commonCodeId) {
        log.info("menuRights listByCommonCodeId...");
        List<MenuRights> result = menuRightsRepository.listByCommonCodeId(commonCodeId);
        List<MenuRightsDTO> dtoList = result.stream()
                .map(menuRights -> modelMapper.map(menuRights, MenuRightsDTO.class))
                .collect(Collectors.toList());
        log.info(dtoList);
        return dtoList;
    }


}
