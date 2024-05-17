package com.example.pjymes.dto;

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
public class ProductionPlanDTO {

    private String planNo;
    private Long orderSubId;
    private String orderNo;
    private String orderSubCustomerCode;
    private String orderSubCustomerName;
    private String orderSubItemCode;
    private String orderSubItemName;
    private String orderSubItemSpecification;
    private Long quantity;
    private LocalDate orderDate;
    private LocalDate dueDate;


    private LocalDateTime startDate;
    private LocalDateTime endDate;
}