package com.example.pjymes.domain;

import com.example.pjymes.dto.ItemDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item extends BaseEntity{

    @Id
    private String code;
    private String name;
    private String specification;
    @Column(nullable = false)
    private Long unitPrice;
    private Boolean active;

    @Setter
    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id")
    private CommonCode category;

    public void change(ItemDTO itemDTO){
        this.name = itemDTO.getName();
        this.specification = itemDTO.getSpecification();
        this.unitPrice = itemDTO.getUnitPrice();
        this.active = itemDTO.getActive();
    }
}