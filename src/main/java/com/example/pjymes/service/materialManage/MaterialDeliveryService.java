package com.example.pjymes.service.materialManage;

import com.example.pjymes.dto.DeliveryDTO;
import com.example.pjymes.dto.LotMasterDTO;
import com.example.pjymes.dto.SearchDTO;
import com.example.pjymes.dto.WarehousingDTO;

import java.util.List;

public interface MaterialDeliveryService {

    Long register(List<DeliveryDTO> deliveryDTOList);

    List<DeliveryDTO> list(SearchDTO searchDTO);

    List<LotMasterDTO> lotList(String code);

}
