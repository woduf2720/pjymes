package com.example.pjymes.repository;

import com.example.pjymes.domain.Customer;
import com.example.pjymes.domain.Item;
import com.example.pjymes.domain.OrderMaster;
import com.example.pjymes.domain.OrderSub;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class OrderMasterRepositoryTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderSubRepository orderSubRepository;

    @Test
    public void testInsert() {

        OrderSub orderSub1 = OrderSub.builder()
                .item(Item.builder().itemCode("P0001").build())
                .quantity(5L)
                .unitPrice(10000L)
                .price(50000L)
                .build();

        OrderSub orderSub2 = OrderSub.builder()
                .item(Item.builder().itemCode("P0002").build())
                .quantity(3L)
                .unitPrice(15000L)
                .price(45000L)
                .build();

        List<OrderSub> orderSubs = new ArrayList<>();
        orderSubs.add(orderSub1);
        orderSubs.add(orderSub2);

        OrderMaster orderMaster = OrderMaster.builder()
                .orderNo("240422-C0001-01")
                .customer(Customer.builder().customerCode("C0001").build())
                .deliveryDate(LocalDateTime.of(2024, 4, 22, 12, 30, 0))
                .price(50000L)
                .build();

        // when
        OrderMaster savedOrderMaster = orderMasterRepository.save(orderMaster);
        orderSubRepository.saveAll(orderSubs);

        // then
            log.info("orderNo : " + savedOrderMaster.getOrderNo());
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
