package com.example.pjymes.service.standardManage;

import com.example.pjymes.domain.Bom;
import com.example.pjymes.domain.Customer;
import com.example.pjymes.domain.Item;
import com.example.pjymes.dto.BomDTO;
import com.example.pjymes.dto.CustomerDTO;
import com.example.pjymes.repository.BomRepository;
import com.example.pjymes.repository.ItemRepository;
import com.example.pjymes.service.standardManage.BomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BomServiceImpl implements BomService {

    private final ModelMapper modelMapper;
    private final BomRepository bomRepository;
    private final ItemRepository itemRepository;

    @Override
    public Long register(BomDTO bomDTO) {
        log.info("bom register...");
        Bom bom = modelMapper.map(bomDTO, Bom.class);

        return bomRepository.save(bom).getId();
    }

    @Override
    public List<BomDTO> readOne(String itemCode) {
        log.info("bom readOne...");
        Queue<String> searchList = new ArrayDeque<>();

        searchList.add(itemCode);
//        List<Bom> bomList = bomRepository.findByParentItemCode(searchList.remove());
//        List<BomDTO> bomDTOList = bomList.stream().map(bom -> modelMapper.map(bom, BomDTO.class)).toList();
//        System.out.println(bomDTOList);
        Item item = itemRepository.findById(itemCode).orElseThrow();
        BomDTO bomDTO = BomDTO.builder()
                .itemCode(item.getCode())
                .itemName(item.getName())
                .itemSpecification(item.getSpecification())
                .level(1L)
                .build();

        List<BomDTO> bomDTOList = new ArrayList<>();

        bomDTOList.add(bomTreeMaking(searchList, bomDTO, 1L));

        log.info(bomDTOList);
        return bomDTOList;
    }

    @Override
    public void modify(BomDTO bomDTO) {
        log.info("bom modify...");
        Optional<Bom> result = bomRepository.findById(bomDTO.getId());
        Bom bom = result.orElseThrow();
        bom.change(bomDTO.getQuantity());
        bomRepository.save(bom);
    }

    @Override
    public void remove(Long id) {
        log.info("bom remove...");
        bomRepository.deleteById(id);
    }

    @Override
    public List<BomDTO> list() {
        log.info("bom list...");
        List<Bom> result = bomRepository.findAll();
        List<BomDTO> dtoList = new ArrayList<>();

        log.info(dtoList);
        return dtoList;
    }

    private BomDTO bomTreeMaking(Queue<String> searchList, BomDTO bomDTO, Long level) {
        if(!searchList.isEmpty()){
            String search = searchList.remove();
            List<Bom> tempBomList = bomRepository.findByParentItemCode(search);
            level += 1;

            for (Bom bom : tempBomList) {
                searchList.add(bom.getItem().getCode());
                BomDTO tempDTO = modelMapper.map(bom, BomDTO.class);
                tempDTO.setLevel(level);
                bomDTO.getChildren().add(tempDTO);
                bomTreeMaking(searchList, tempDTO, level);
            }
        }
        return bomDTO;
    }

}
