package com.example.pjymes.dto;

import lombok.Data;

@Data
public class BomDTO {

    private Long bomId;
    private String parentItem;
    private String childItem;
}
