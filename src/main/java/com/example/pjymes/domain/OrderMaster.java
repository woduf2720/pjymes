package com.example.pjymes.domain;

import com.example.pjymes.dto.OrderMasterDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    @Column(nullable = false)
    private Long price;
    private Boolean active;

    public void change(LocalDate orderDate, LocalDate deliveryDate, Long price){
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.price = price;
    }

    public void changeActive(Boolean active){
        this.active = active;
    }
}