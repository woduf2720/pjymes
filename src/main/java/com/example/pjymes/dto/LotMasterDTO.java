package com.example.pjymes.dto;

import com.example.pjymes.domain.Item;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LotMasterDTO {

    private String lotNo;

    private String itemCode;
    private String itemName;
    private String itemSpecification;
    private String itemCategoryName;
    private Long itemUnitPrice;
    private Long quantity;
}