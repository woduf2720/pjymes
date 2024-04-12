package com.example.pjymes.service;

import com.example.pjymes.domain.Item;
import com.example.pjymes.domain.Member;
import com.example.pjymes.dto.ItemDTO;
import com.example.pjymes.dto.MemberDTO;
import com.example.pjymes.repository.ItemRepository;
import com.example.pjymes.repository.MemberRepository;
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
public class ItemManageServiceImpl implements ItemManageService{

    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;

    @Override
    public String register(ItemDTO itemDTO) {
        return null;
    }

    @Override
    public ItemDTO readOne(Long itemCode) {
        return null;
    }

    @Override
    public void modify(ItemDTO itemDTO) {

    }

    @Override
    public List<ItemDTO> list() {
        log.info("itemlist...");
        List<Item> result = itemRepository.findAll();
        List<ItemDTO> dtoList = result.stream()
                .map(item -> modelMapper.map(item, ItemDTO.class)).collect(Collectors.toList());
        return dtoList;
    }

}
