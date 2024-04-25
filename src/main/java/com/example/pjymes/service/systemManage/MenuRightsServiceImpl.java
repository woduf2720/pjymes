package com.example.pjymes.service.systemManage;

import com.example.pjymes.domain.MenuRights;
import com.example.pjymes.dto.MenuRightsDTO;
import com.example.pjymes.repository.MenuRightsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MenuRightsServiceImpl implements MenuRightsService{

    private final ModelMapper modelMapper;
    private final MenuRightsRepository menuRightsRepository;

    @Override
    public Long register(MenuRightsDTO menuRightsDTO) {
        log.info("menuRights register...");
        MenuRights menuRights = modelMapper.map(menuRightsDTO, MenuRights.class);
        return menuRightsRepository.save(menuRights).getId();
    }

    @Override
    public void modify(MenuRightsDTO menuRightsDTO) {
        log.info("menuRights modify...");
        Optional<MenuRights> result = menuRightsRepository.findById(menuRightsDTO.getId());
        MenuRights menuRights = result.orElseThrow();
        menuRights.change(menuRightsDTO);
        menuRightsRepository.save(menuRights);
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
    public List<MenuRightsDTO> listByUserTypeId(Long userTypeId) {
        log.info("menuRights listByUserTypeId...");
        List<MenuRights> result = menuRightsRepository.findByUserTypeId(userTypeId);
        return result.stream()
                .map(menuRights -> modelMapper.map(menuRights, MenuRightsDTO.class))
                .collect(Collectors.toList());
    }


}
