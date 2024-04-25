package com.example.pjymes.repository;

import com.example.pjymes.domain.MenuRights;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRightsRepository extends JpaRepository<MenuRights, Long> {
    List<MenuRights> findByUserTypeId(Long userTypeId);
}
