package com.example.pjymes.service;

import com.example.pjymes.domain.Menu;
import com.example.pjymes.dto.MenuDTO;
import com.example.pjymes.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Menu menu = modelMapper.map(menuDTO, Menu.class);
        Long menuCode = menuRepository.save(menu).getMenuId();
        return menuCode;
    }

    @Override
    public MenuDTO readOne(Long menuId) {
        log.info("menu readOne...");
        Optional<Menu> result = menuRepository.findById(menuId);

        Menu menu = result.orElseThrow();

        MenuDTO menuDTO = modelMapper.map(menu, MenuDTO.class);

        return menuDTO;
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public void modify(MenuDTO menuDTO) {
        log.info("menu modify...");
        Optional<Menu> result = menuRepository.findById(menuDTO.getMenuId());
        System.out.println(result);
        Menu menu = result.orElseThrow();
        menu.change(menuDTO.getDisplayOrder(), menuDTO.getMenuName(), menuDTO.getUrl());
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
            if (menu.getParentId() == null) {
                dtoList.add(convertToDTO(menu, result));
            }
        }

        log.info(dtoList);
        return dtoList;
    }

    // 재귀적으로 메뉴를 순회하면서 DTO로 변환
    private MenuDTO convertToDTO(Menu menu, List<Menu> menuList) {
        log.info("menu convertToDTO...");
        MenuDTO menudto = modelMapper.map(menu, MenuDTO.class);

        // 자식 메뉴가 있는 경우 재귀적으로 처리
        for (Menu childMenu : menuList) {
            if (childMenu.getParentId() != null && childMenu.getParentId().equals(menu.getMenuId())) {
                menudto.getChildren().add(convertToDTO(childMenu, menuList));
            }
        }

        return menudto;
    }
}
