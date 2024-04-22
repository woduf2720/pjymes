package com.example.pjymes.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderSub {

    @EmbeddedId
    private OrderSubId orderSubId;

    // OrderMaster를 설정하는 메서드
    @ManyToOne
    @MapsId("orderNo")
    @JoinColumn(name = "order_no")
    private OrderMaster orderMaster;

    @ManyToOne
    @MapsId("itemCode")
    @JoinColumn(name = "item_code")
    private Item item;

    private Long quantity;
    private Long unitPrice;
    private Long price;
}