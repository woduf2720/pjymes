package com.example.pjymes.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductWarehousing extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "lotNo")
    private LotMaster lotMaster;

    @ManyToOne
    @JoinColumn(name = "itemCode")
    private Item item;

    private Long quantity;
}