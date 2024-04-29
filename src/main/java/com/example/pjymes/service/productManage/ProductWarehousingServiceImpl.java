package com.example.pjymes.service.productManage;

import com.example.pjymes.domain.Item;
import com.example.pjymes.domain.LotMaster;
import com.example.pjymes.domain.ProductWarehousing;
import com.example.pjymes.dto.ProductWarehousingDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.repository.ItemRepository;
import com.example.pjymes.repository.LotMasterRepository;
import com.example.pjymes.repository.ProductWarehousingRepository;
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
public class ProductWarehousingServiceImpl implements ProductWarehousingService {

    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;
    private final LotMasterRepository lotMasterRepository;
    private final ProductWarehousingRepository productWarehousingRepository;

    @Override
    @Transactional
    public Long register(List<ProductWarehousingDTO> productWarehousingDTOList) {
        log.info("register..." + productWarehousingDTOList);

        for(ProductWarehousingDTO productWarehousingDTO : productWarehousingDTOList) {
            log.info("register..." + productWarehousingDTO);
            //입고 저장할때 작동해야되는 기능
            System.out.println(productWarehousingDTO.getItemCode());
            Item item = itemRepository.findById(productWarehousingDTO.getItemCode()).orElseThrow();

            //Lot생성 후 lotMaster 저장
            String newLotNo = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")) + productWarehousingDTO.getItemCode();
            String getLotNo = newLotNo + lotMasterRepository.getLotNo(newLotNo);

            LotMaster lotMaster = LotMaster.builder()
                    .lotNo(getLotNo)
                    .item(item)
                    .quantity(productWarehousingDTO.getQuantity())
                    .build();
            lotMaster = lotMasterRepository.save(lotMaster);
            log.info("lot저장");

            //warehousing 저장
            ProductWarehousing productWarehousing = modelMapper.map(productWarehousingDTO, ProductWarehousing.class);
            productWarehousing.setLotMaster(lotMaster);
            productWarehousingRepository.save(productWarehousing);
            log.info("warehouing저장");
        }

        return 1L;
    }

    @Override
    public List<ProductWarehousingDTO> list(SearchDTO searchDTO) {
        log.info("lotMaster list...");
        List<ProductWarehousing> result = productWarehousingRepository.findAll();

        return result.stream()
                .map(productWarehousing -> modelMapper.map(productWarehousing, ProductWarehousingDTO.class))
                .collect(Collectors.toList());
    }
}
