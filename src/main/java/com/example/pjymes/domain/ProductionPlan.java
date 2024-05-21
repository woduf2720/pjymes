package com.example.pjymes.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductionPlan {

    @Id
    private String planNo;

    @Setter
    @ManyToOne
    @JoinColumn(name = "orderSubId")
    private ProductOrderSub productOrderSub;

    @Column(nullable = false)
    private Long orderQuantity;

    @Builder.Default
    @Column(nullable = false)
    private Long productionQuantity = 0L;
    private LocalDate orderDate;
    private LocalDate dueDate;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private int status;

    public void changeProductionQuantity(Long productionQuantity){
        this.productionQuantity = productionQuantity;
    }

    public void changeStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }

    public void changeEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }

    public void changeStatus(int status){
        this.status = status;
    }

}