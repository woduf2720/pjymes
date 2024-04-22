package com.example.pjymes.domain;

import com.example.pjymes.dto.ItemDTO;
import com.example.pjymes.dto.OrderMasterDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderMaster extends BaseEntity{

    @Id
    private String orderNo;
    @ManyToOne
    @JoinColumn(name = "customerCode")
    private Customer customer;
    private LocalDateTime deliveryDate;
    private Long price;

    @OneToMany(mappedBy = "orderMaster", cascade = CascadeType.ALL)
    private List<OrderSub> orderSubs;

    public void change(OrderMasterDTO orderMasterDTO){
        this.deliveryDate = orderMasterDTO.getDeliveryDate();
        this.price = orderMasterDTO.getPrice();
    }
}