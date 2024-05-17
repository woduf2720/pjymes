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
    private Long quantity;
    private LocalDate orderDate;
    private LocalDate dueDate;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public void change(Long quantity,LocalDateTime startDate, LocalDateTime endDate){
        this.quantity = quantity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}