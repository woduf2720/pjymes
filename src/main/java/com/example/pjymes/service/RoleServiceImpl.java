package com.example.pjymes.service;

import com.example.pjymes.domain.Role;
import com.example.pjymes.dto.RoleDTO;
import com.example.pjymes.repository.RoleRepository;
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
public class RoleServiceImpl implements RoleService {

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Override
    public RoleDTO readOne(Long id) {
        log.info("role readOne...");
        Optional<Role> result = roleRepository.findById(id);
        Role role = result.orElseThrow();
        return modelMapper.map(role, RoleDTO.class);
    }

    @Override
    public List<RoleDTO> list() {
        log.info("lotMaster list...");
        List<Role> result = roleRepository.findAll();

        return result.stream()
                .map(role -> modelMapper.map(role, RoleDTO.class))
                .collect(Collectors.toList());
    }
}
