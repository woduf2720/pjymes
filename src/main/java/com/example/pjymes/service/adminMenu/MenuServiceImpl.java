package com.example.pjymes.service.adminMenu;

import com.example.pjymes.domain.Menu;
import com.example.pjymes.dto.MenuDTO;
import com.example.pjymes.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MenuServiceImpl implements MenuService{

    private final ModelMapper modelMapper;
    private final MenuRepository menuRepository;

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public Long register(MenuDTO menuDTO) {
        log.info("menu register...");
        Long parentId = menuDTO.getParentId();
        long count = menuRepository.findAllById(parentId).size();
        menuDTO.setOrderIndex((int) count+1);

        Menu menu = modelMapper.map(menuDTO, Menu.class);
        if(parentId != null){
            menu.setParent(menuRepository.findById(parentId).orElseThrow());
        }

        return menuRepository.save(menu).getId();
    }

    @Override
    public MenuDTO readOne(Long menuId) {
        log.info("menu readOne...");
        Optional<Menu> result = menuRepository.findById(menuId);
        Menu menu = result.orElseThrow();
        return modelMapper.map(menu, MenuDTO.class);
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public void modify(MenuDTO menuDTO) {
        log.info("menu modify...");
        Optional<Menu> result = menuRepository.findById(menuDTO.getId());
        Menu menu = result.orElseThrow();
        menu.change(menuDTO.getOrderIndex(), menuDTO.getName(), menuDTO.getUrl());
        menuRepository.save(menu);
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public void remove(Long menuCode) {
        log.info("menu remove...");
        menuRepository.deleteById(menuCode);
    }

    @Cacheable("menu")
    @Override
    public List<MenuDTO> list() {
        log.info("menu list...");
        List<Menu> result = menuRepository.findAll();
        List<MenuDTO> dtoList = new ArrayList<>();

        // 부모가 없는 최상위 메뉴들을 찾아서 계층 구조 생성
        for (Menu menu : result) {
            if (menu.getParent() == null) {
                dtoList.add(convertToDTO(menu, result));
            }
        }

        log.info(dtoList);
        return dtoList;
    }

    private MenuDTO convertToDTO(Menu menu, List<Menu> menuList) {
        MenuDTO menuDto = modelMapper.map(menu, MenuDTO.class);

        // 자식 메뉴가 있는 경우 재귀적으로 처리
        for (Menu childMenu : menuList) {
            if (childMenu.getParent() != null && childMenu.getParent().getId().equals(menu.getId())) {
                menuDto.getChildren().add(convertToDTO(childMenu, menuList));
            }
        }

        return menuDto;
    }
}
