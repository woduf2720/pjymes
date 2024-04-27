package com.example.pjymes.repository;

import com.example.pjymes.domain.Customer;
import com.example.pjymes.domain.Item;
import com.example.pjymes.domain.OrderMaster;
import com.example.pjymes.domain.OrderSub;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
public class MaterialOrderRepositoryTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderSubRepository orderSubRepository;

    @Test
    public void testInsert() {
        log.info("테스트 시작");
        OrderMaster orderMaster = OrderMaster.builder()
                .orderNo("240424-C0001-01")
                .customer(Customer.builder().code("C0001").build())
                .orderDate(LocalDate.of(2024, 4, 24))
                .deliveryDate(LocalDate.of(2024, 4, 24))
                .price(50000L)
                .build();

        OrderSub orderSub1 = OrderSub.builder()
                .item(Item.builder().code("P0001").build())
                .quantity(5L)
                .unitPrice(10000L)
                .price(50000L)
                .orderMaster(orderMaster)
                .build();

        OrderSub orderSub2 = OrderSub.builder()
                .item(Item.builder().code("P0002").build())
                .quantity(3L)
                .unitPrice(15000L)
                .price(45000L)
                .orderMaster(orderMaster)
                .build();

        List<OrderSub> orderSubs = new ArrayList<>();
        orderSubs.add(orderSub1);
        orderSubs.add(orderSub2);

        // when
        orderMasterRepository.save(orderMaster);
        orderSubRepository.saveAll(orderSubs);
        // then
        log.info("테스트 완료 ");
    }

    @Test
    public void testSelect() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testDelete() {
    }
}
