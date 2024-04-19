package com.example.pjymes.dto;

import lombok.Data;

@Data
public class ItemDTO {

    private String itemCode;
    private String itemName;
    private String standard;
    private String category;
    private Long unitPrice;
    private Boolean useStatus;
}
