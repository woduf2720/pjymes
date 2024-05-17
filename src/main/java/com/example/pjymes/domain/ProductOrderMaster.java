package com.example.pjymes.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductOrderMaster extends BaseEntity{

    @Id
    private String orderNo;
    @ManyToOne
    @JoinColumn(name = "customerCode")
    private Customer customer;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    @Column(nullable = false)
    private Long price;
    private OrderStatus orderStatus;

    public void change(LocalDate orderDate, LocalDate deliveryDate, Long price){
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.price = price;
    }

    public void changeOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }
}