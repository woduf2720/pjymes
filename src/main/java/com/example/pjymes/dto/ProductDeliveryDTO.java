package com.example.pjymes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDeliveryDTO {

    private Long id;
    private String lotNo;
    private String itemCode;
    private String itemName;
    private String itemSpecification;
    private String itemCategoryName;
    private Long itemUnitPrice;
    private Long quantity;

    private Long orderSubId;
    private String orderNo;
    private String orderMasterCustomerCode;
    private String orderMasterCustomerName;
    private LocalDate orderMasterOrderDate;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}