package com.example.pjymes.repository.MenuRights;

import com.example.pjymes.domain.MenuRights;

import java.util.List;

public interface CustomMenuRightsRepository {

    List<MenuRights> listBySubCode(String subCode);

}
