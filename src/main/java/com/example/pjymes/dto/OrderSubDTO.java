package com.example.pjymes.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSubDTO {

    private String orderNo;
    private String itemCode;
    private String itemName;
    private String standard;
    private Long quantity;
    private Long unitPrice;
    private Long price;
}