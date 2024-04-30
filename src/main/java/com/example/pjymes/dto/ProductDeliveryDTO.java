package com.example.pjymes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDeliveryDTO {

    private Long id;
    private String orderNo;
    private String lotNo;
    private String itemCode;
    private String itemName;
    private String itemSpecification;
    private Long quantity;
    private Long itemUnitPrice;
    private Long price;
}