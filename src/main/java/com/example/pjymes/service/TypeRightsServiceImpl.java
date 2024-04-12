package com.example.pjymes.service;

import com.example.pjymes.domain.Menu;
import com.example.pjymes.domain.TypeRights;
import com.example.pjymes.domain.TypeRightsId;
import com.example.pjymes.dto.CommonCodeDTO;
import com.example.pjymes.dto.MenuDTO;
import com.example.pjymes.dto.TypeRightsDTO;
import com.example.pjymes.repository.MenuRepository;
import com.example.pjymes.repository.TypeRightsRepository;
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
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class TypeRightsServiceImpl implements TypeRightsService{

    private final ModelMapper modelMapper;
    private final TypeRightsRepository typeRightsRepository;

    @Override
    public TypeRightsId register(TypeRightsDTO typeRightsDTO) {
        log.info("typeRights register...");
        TypeRights typeRights = modelMapper.map(typeRightsDTO, TypeRights.class);
        TypeRightsId typeRightsId = typeRightsRepository.save(typeRights).getTypeRightsId();
        return typeRightsId;
    }

    @Override
    public void modify(TypeRightsDTO typeRightsDTO) {
        log.info("typeRights modify...");
        TypeRightsId typeRightsId = new TypeRightsId(typeRightsDTO.getTypeId(), typeRightsDTO.getMenuId());
        Optional<TypeRights> result = typeRightsRepository.findById(typeRightsId);
        TypeRights typeRights = result.orElseThrow();
        typeRights.change(typeRightsDTO.getTypeName());
        typeRightsRepository.save(typeRights);
    }

    @Override
    public List<TypeRightsDTO> list() {
        log.info("typeRights list...");
        List<TypeRights> result = typeRightsRepository.findAll();
        List<TypeRightsDTO> dtoList = result.stream()
                .map(typeRights -> modelMapper.map(typeRights, TypeRightsDTO.class)).collect(Collectors.toList());

        log.info(dtoList);
        return dtoList;
    }

    @Override
    public List<TypeRightsDTO> listBySubCode(String subCode) {
        log.info("typeRights listBySubCode...");
        List<TypeRights> result = typeRightsRepository.listBySubCode(subCode);
        List<TypeRightsDTO> dtoList = result.stream()
                .map(typeRights -> modelMapper.map(typeRights, TypeRightsDTO.class)).collect(Collectors.toList());

        log.info(dtoList);
        return dtoList;
    }


}
