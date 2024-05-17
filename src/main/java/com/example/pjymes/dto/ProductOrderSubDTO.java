package com.example.pjymes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderSubDTO {

    private Long orderSubId;
    private String orderNo;
    private String orderMasterCustomerCode;
    private String orderMasterCustomerName;
    private LocalDate orderMasterOrderDate;
    private LocalDate orderMasterDeliveryDate;

    private String itemCode;
    private String itemName;
    private String itemSpecification;
    private String itemCategoryName;
    private Long quantity;
    private Long deliveryQuantity;
    private Long unitPrice;
    private Long price;
}