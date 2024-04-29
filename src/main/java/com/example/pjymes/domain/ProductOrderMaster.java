package com.example.pjymes.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Long price;
    private Boolean active = false;

    public void change(LocalDate orderDate, LocalDate deliveryDate, Long price){
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.price = price;
    }

    public void changeActive(Boolean active){
        this.active = active;
    }
}