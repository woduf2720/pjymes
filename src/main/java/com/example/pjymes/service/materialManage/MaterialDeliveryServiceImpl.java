package com.example.pjymes.service.materialManage;

import com.example.pjymes.domain.*;
import com.example.pjymes.dto.DeliveryDTO;
import com.example.pjymes.dto.LotMasterDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.dto.WarehousingDTO;
import com.example.pjymes.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MaterialDeliveryServiceImpl implements MaterialDeliveryService {

    private final ModelMapper modelMapper;
    private final LotMasterRepository lotMasterRepository;
    private final MaterialDeliveryRepository materialDeliveryRepository;

    @Override
    @Transactional
    public Long register(List<DeliveryDTO> deliveryDTOList) {
        log.info("register..." + deliveryDTOList);

        for(DeliveryDTO deliveryDTO : deliveryDTOList) {
            log.info("register..." + deliveryDTO);
            //입고 저장할때 작동해야되는 기능

            // lotMaster 수량 변경
            String lotNo = deliveryDTO.getLotNo();
            LotMaster lotMaster = lotMasterRepository.findById(lotNo).orElseThrow();
            lotMaster.change(lotMaster.getQuantity() - deliveryDTO.getQuantity());
            lotMaster = lotMasterRepository.save(lotMaster);
            log.info("lot저장");

            //delivery 저장
            Delivery delivery = modelMapper.map(deliveryDTO, Delivery.class);
            delivery.setLotMaster(lotMaster);
            materialDeliveryRepository.save(delivery);
            log.info("warehouing저장");
        }

        return 1L;
    }

    @Override
    public List<DeliveryDTO> list(SearchDTO searchDTO) {
        log.info("lotMaster list...");
        List<Delivery> result = materialDeliveryRepository.findAll();

        return result.stream()
                .map(delivery -> modelMapper.map(delivery, DeliveryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LotMasterDTO> lotList(String code) {
        log.info("lotMaster list...");
        List<LotMaster> result = lotMasterRepository.findByItemCodeAndQuantityIsNot(code, 0L);

        return result.stream()
                .map(lotMaster -> modelMapper.map(lotMaster, LotMasterDTO.class))
                .collect(Collectors.toList());
    }
}
