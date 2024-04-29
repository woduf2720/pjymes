package com.example.pjymes.service;

import com.example.pjymes.dto.OrderMasterDTO;
import com.example.pjymes.dto.OrderSubDTO;
import com.example.pjymes.service.materialManage.MaterialOrderService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
public class ProductOrderServiceTests {

    @Autowired
    private MaterialOrderService materialOrderService;

    @Test
    public void testRegister() {

        OrderSubDTO orderSubDTO1 = OrderSubDTO.builder()
                .itemCode("P0001")
                .quantity(5L)
                .unitPrice(10000L)
                .price(50000L)
                .orderNo("240424-C0002-01")
                .build();

        OrderSubDTO orderSubDTO2 = OrderSubDTO.builder()
                .itemCode("P0002")
                .quantity(3L)
                .unitPrice(15000L)
                .price(45000L)
                .orderNo("240424-C0002-01")
                .build();

        List<OrderSubDTO> orderSubDTOList = new ArrayList<>();
        orderSubDTOList.add(orderSubDTO1);
        orderSubDTOList.add(orderSubDTO2);

        OrderMasterDTO orderMasterDTO = OrderMasterDTO.builder()
                .orderNo("240424-C0002-01")
                .customerCode("C0002")
                .orderDate(LocalDate.parse("2024-04-24"))
                .deliveryDate(LocalDate.parse("2024-04-24"))
                .price(50000L)
                .orderSubDTOList(orderSubDTOList)
                .build();

        materialOrderService.register(orderMasterDTO);
    }

    @Test
    public void testList() {

    }

    @Test
    public void testDelete() {
    }
}
