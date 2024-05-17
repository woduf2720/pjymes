package com.example.pjymes.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LotMaster extends BaseEntity{

    @Id
    private String lotNo;

    @ManyToOne
    @JoinColumn(name = "itemCode")
    private Item item;

    @Column(nullable = false)
    private Long quantity;


    public void change(Long quantity){
        this.quantity = quantity;
    }
}