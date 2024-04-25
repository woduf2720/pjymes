package com.example.pjymes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private String code;
    private String name;
    private String specification;
    private String category;
    private Long unitPrice;
    private Boolean active;
}
