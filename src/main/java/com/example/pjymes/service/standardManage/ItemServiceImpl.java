package com.example.pjymes.service.standardManage;

import com.example.pjymes.domain.Item;
import com.example.pjymes.dto.ItemDTO;
import com.example.pjymes.repository.ItemRepository;
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
public class ItemServiceImpl implements ItemService {

    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;

    @Override
    public String register(ItemDTO itemDTO) {
        log.info("item register...");
        Item item = modelMapper.map(itemDTO, Item.class);
        return itemRepository.save(item).getItemCode();
    }

    @Override
    public ItemDTO readOne(String itemCode) {
        log.info("item readOne...");
        Optional<Item> result = itemRepository.findById(itemCode);
        Item item = result.orElseThrow();
        return modelMapper.map(item, ItemDTO.class);
    }

    @Override
    public void modify(ItemDTO itemDTO) {
        log.info("item modify...");
        Optional<Item> result = itemRepository.findById(itemDTO.getItemCode());
        Item item = result.orElseThrow();
        item.change(itemDTO);
        itemRepository.save(item);
    }

    @Override
    public List<ItemDTO> list() {
        log.info("item list...");
        List<Item> result = itemRepository.findAll();
        return result.stream()
                .map(item -> modelMapper.map(item, ItemDTO.class)).collect(Collectors.toList());
    }

}
