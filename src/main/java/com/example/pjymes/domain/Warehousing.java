package com.example.pjymes.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Warehousing extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "lotNo")
    private LotMaster lotMaster;

    @Setter
    @ManyToOne
    @JoinColumn(name = "orderNo")
    private OrderMaster orderMaster;

    @ManyToOne
    @JoinColumn(name = "itemCode")
    private Item item;

    @Column(nullable = false)
    private Long quantity;
    private Long unitPrice;
    private Long price;
}