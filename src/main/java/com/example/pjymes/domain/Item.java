package com.example.pjymes.domain;

import com.example.pjymes.dto.ItemDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

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
    private String category;
    private Long unitPrice;
    private Boolean active;

    public void change(ItemDTO itemDTO){
        this.name = itemDTO.getName();
        this.specification = itemDTO.getSpecification();
        this.category = itemDTO.getCategory();
        this.unitPrice = itemDTO.getUnitPrice();
        this.active = itemDTO.getActive();
    }
}