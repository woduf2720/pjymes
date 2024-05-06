package com.example.pjymes.service;

import com.example.pjymes.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    RoleDTO readOne(Long id);

    List<RoleDTO> list();
}
