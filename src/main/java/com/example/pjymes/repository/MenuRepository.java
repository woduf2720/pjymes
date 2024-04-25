package com.example.pjymes.repository;

import com.example.pjymes.domain.Menu;
import com.example.pjymes.dto.MenuDTO;
import com.example.pjymes.repository.menu.CustomMenuRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long>, CustomMenuRepository {
    List<Menu> findAllById(Long id);

}
