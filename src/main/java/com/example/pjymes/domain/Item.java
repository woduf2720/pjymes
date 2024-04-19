package com.example.pjymes.domain;

import com.example.pjymes.dto.ItemDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseEntity{

    @Id
    private String itemCode;
    private String itemName;
    private String standard;
    private String category;
    private Long unitPrice;
    private Boolean useStatus;

    public void change(ItemDTO itemDTO){
        this.itemName = itemDTO.getItemName();
        this.standard = itemDTO.getStandard();
        this.category = itemDTO.getCategory();
        this.unitPrice = itemDTO.getUnitPrice();
        this.useStatus = itemDTO.getUseStatus();
    }
}