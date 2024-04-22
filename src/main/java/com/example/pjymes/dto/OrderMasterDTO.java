package com.example.pjymes.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderMasterDTO {

    private String orderNo;
    private String customerCode;
    private String customerName;
    private LocalDateTime deliveryDate;
    private Long price;
    private List<OrderSubDTO> orderSubs;
}
