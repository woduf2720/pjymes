package com.example.pjymes.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"orderNo", "itemCode"}))
public class OrderSub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderSubId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "orderNo")
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private OrderMaster orderMaster;

    @ManyToOne
    @JoinColumn(name = "itemCode")
    private Item item;

    @Column(nullable = false)
    private Long quantity;
    @Builder.Default
    @Column(nullable = false)
    private Long warehousingQuantity = 0L;
    private Long unitPrice;
    private Long price;

    public void change(Long warehousingQuantity){
        this.warehousingQuantity = warehousingQuantity;
    }
}