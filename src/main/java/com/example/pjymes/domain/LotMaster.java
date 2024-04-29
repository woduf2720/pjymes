package com.example.pjymes.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LotMaster {

    @Id
    private String lotNo;

    @ManyToOne
    @JoinColumn(name = "itemCode")
    private Item item;

    private Long quantity;


    public void change(Long quantity){
        this.quantity = quantity;
    }
}