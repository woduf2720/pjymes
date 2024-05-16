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
public class WarehousingDTO {

    private Long id;
    private String orderNo;
    private String orderMasterCustomerCode;
    private String orderMasterCustomerName;
    private LocalDate orderMasterOrderDate;

    private String lotNo;
    private String itemCode;
    private String itemName;
    private String itemSpecification;
    private Long quantity;
    private Long unitPrice;
    private Long price;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}