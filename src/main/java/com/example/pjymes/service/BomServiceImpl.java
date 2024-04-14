package com.example.pjymes.service;

import com.example.pjymes.domain.Bom;
import com.example.pjymes.domain.Item;
import com.example.pjymes.dto.BomDTO;
import com.example.pjymes.dto.ItemDTO;
import com.example.pjymes.repository.BomRepository;
import com.example.pjymes.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BomServiceImpl implements BomService {

    private final ModelMapper modelMapper;
    private final BomRepository bomRepository;

    @Override
    public Long register(BomDTO bomDTO) {
        return null;
    }

    @Override
    public BomDTO readOne(Long bomId) {
        return null;
    }

    @Override
    public void modify(BomDTO bomDTO) {

    }

    @Override
    public List<BomDTO> list() {
        log.info("itemlist...");
        List<Bom> result = bomRepository.findAll();
        List<BomDTO> dtoList = result.stream()
                .map(bom -> modelMapper.map(bom, BomDTO.class)).collect(Collectors.toList());
        return dtoList;
    }

}
