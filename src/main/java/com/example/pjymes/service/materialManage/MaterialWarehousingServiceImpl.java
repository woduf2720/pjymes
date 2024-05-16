package com.example.pjymes.service.materialManage;

import com.example.pjymes.domain.*;
import com.example.pjymes.dto.MenuRightsDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.dto.WarehousingDTO;
import com.example.pjymes.repository.LotMasterRepository;
import com.example.pjymes.repository.MaterialWarehousingRepository;
import com.example.pjymes.repository.OrderMasterRepository;
import com.example.pjymes.repository.OrderSubRepository;
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
public class MaterialWarehousingServiceImpl implements MaterialWarehousingService {

    private final ModelMapper modelMapper;
    private final OrderMasterRepository orderMasterRepository;
    private final OrderSubRepository orderSubRepository;
    private final MaterialWarehousingRepository materialWarehousingRepository;
    private final LotMasterRepository lotMasterRepository;

    @Override
    @Transactional
    public String register(List<WarehousingDTO> warehousingDTOList) {
        log.info("register..." + warehousingDTOList);
        String orderNo = warehousingDTOList.get(0).getOrderNo();
        OrderMaster orderMaster = orderMasterRepository.findById(orderNo).orElseThrow();

        for(WarehousingDTO warehousingDTO : warehousingDTOList) {
            log.info("register..." + warehousingDTO);
            //입고 저장할때 작동해야되는 기능
            //orderSub 입고수량 변경
            OrderSub orderSub = orderSubRepository.findById(warehousingDTO.getId()).orElseThrow();

            orderSub.change(warehousingDTO.getQuantity());
            orderSubRepository.save(orderSub);
            log.info("sub저장");

            //Lot생성 후 lotMaster 저장
            String newLotNo = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")) + orderSub.getItem().getCode();
            String getLotNo = newLotNo + lotMasterRepository.getLotNo(newLotNo);

            LotMaster lotMaster = LotMaster.builder()
                    .lotNo(getLotNo)
                    .item(orderSub.getItem())
                    .quantity(warehousingDTO.getQuantity())
                    .build();
            lotMaster = lotMasterRepository.save(lotMaster);
            log.info("lot저장");

            //warehosing 저장
            Warehousing warehousing = modelMapper.map(warehousingDTO, Warehousing.class);
            warehousing.setLotMaster(lotMaster);
            warehousing.setOrderMaster(orderMaster);
            materialWarehousingRepository.save(warehousing);
            log.info("warehouing저장");
        }

        //orderMaster sub입고 전부 다됬으면 완료처리
        OrderStatus orderStatus = orderSubRepository.getQuantityMinusWarehousingQuantityByOrderNo(orderNo);
        orderMaster.changeOrderStatus(orderStatus);
        orderMasterRepository.save(orderMaster);
        log.info("master저장");

        return orderNo;
    }

    @Override
    public List<WarehousingDTO> list(SearchDTO searchDTO) {
        log.info("test list...");
        List<Warehousing> result = materialWarehousingRepository.findByKeyword(searchDTO);
        log.info(result.toString());
        return result.stream()
                .map(warehousing -> modelMapper.map(warehousing, WarehousingDTO.class))
                .collect(Collectors.toList());
    }
}
