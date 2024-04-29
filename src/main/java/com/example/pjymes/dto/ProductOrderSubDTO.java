package com.example.pjymes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderSubDTO {

    private Long orderSubId;
    private String orderNo;
    private String itemCode;
    private String itemName;
    private String itemSpecification;
    private Long quantity;
    private Long warehousingQuantity;
    private Long unitPrice;
    private Long price;
}