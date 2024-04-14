package com.example.pjymes.repository;

import com.example.pjymes.domain.MenuRights;
import com.example.pjymes.domain.MenuRightsId;
import com.example.pjymes.repository.MenuRights.CustomMenuRightsRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRightsRepository extends JpaRepository<MenuRights, MenuRightsId>, CustomMenuRightsRepository {

}
